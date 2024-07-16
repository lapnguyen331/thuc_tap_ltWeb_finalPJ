package com.project.web_socket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.models.User;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.OnOpen;
import jakarta.websocket.*;
import jakarta.websocket.server.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@ServerEndpoint(value = "/chat/{target}", configurator = HttpSessionConfigurator.class)
public class ChatEndpoint {
    static Map<String, Set<Session>> map_user = new HashMap<>(); // user -> [logon sessions]
    static Map<String, List<WSActionDTO>> chatHistory = new HashMap<>();
    // username -> targetname
    private ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @OnOpen
    public void onOpen(Session session, @PathParam("target") String target) throws IOException {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
        if (httpSession.getAttribute("user") == null) {
            session.getBasicRemote().sendText("Bạn chưa đăng nhập, đóng WebSocket này");
            session.close();
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        User user = (User) httpSession.getAttribute("user");
        String username = user.getUsername();
        String message = "Bạn đã đăng nhập với tư cách user: "+username;
        WSActionDTO dto = null;
        dto = createMessage(message);
        session.getBasicRemote().sendText(mapper.writeValueAsString(dto));


        session.getUserProperties().put("username", username);
        if (chatHistory.get(username+"<->"+target) == null) {
            chatHistory.put(username, new ArrayList<>());
        }
        if (chatHistory.get(target+"<->"+username) == null) {
            chatHistory.put(username, new ArrayList<>());
        }
        if (map_user.get(username) == null) {
            map_user.put(username, Collections.synchronizedSet(new HashSet<>()));
        }
        map_user.get(username).add(session);
        var history = loadHistoryDTO(username, target);
        synchronizeData(username, mapper.writeValueAsString(history));
    }

    @OnMessage
    public void onMessage(Session session, @PathParam("target") String target, String requestJSON) throws IOException {
        WSUserRequestDTO requestDTO = mapper.readValue(requestJSON, WSUserRequestDTO.class);
        String username = session.getUserProperties().get("username").toString();
        switch (requestDTO.action) {
            case "send_chat": {
                // Đối với người gửi
                var sendDTO = sendChatDTO(target, requestDTO.data);
                chatHistory.get(username+"<->"+target).add(sendDTO);
                synchronizeData(username, mapper.writeValueAsString(sendDTO));

                // Đối với người nhận
                var receiveDTO = receiveChatDTO(username, requestDTO.data);
                chatHistory.get(target+"<->"+username).add(receiveDTO);
                synchronizeData(target, mapper.writeValueAsString(receiveDTO));
                break;
            }
            case "count_unread": {
                var unreadMsg = getUnreadMessage(target, requestDTO.data);
                synchronizeData(username, mapper.writeValueAsString(unreadMsg));
            }
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("target") String target) throws IOException {

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    public static void synchronizeData(String username, String data) throws IOException {
        if (map_user.get(username) != null) {
            for (var session : map_user.get(username)) {
                session.getBasicRemote().sendText(data);
            }
        }
    }

    public WSActionDTO createMessage(String message) {
        return WSActionDTO.builder()
                .action("message")
                .data(List.of(message))
                .build();
    }

    public WSActionDTO sendChatDTO(String receiver, String message) {
        var send = SendChatDTO.builder()
                .receiver(new SendChatDTO.Receiver(receiver)).message(message)
                .timestamp(LocalDateTime.now()).build();
        return WSActionDTO.builder()
                .data(List.of(send))
                .action("send_chat")
                .build();
    }

    public WSActionDTO receiveChatDTO(String sender, String message) {
        var receive = ReceiveChatDTO.builder()
                .sender(new ReceiveChatDTO.Sender(sender)).message(message)
                .timestamp(LocalDateTime.now()).build();
        return WSActionDTO.builder()
                .data(List.of(receive))
                .action("receive_chat")
                .build();
    }

    public WSActionDTO loadHistoryDTO(String username, String target) {
        String key = username+"<->"+target;
        List<?> history = chatHistory.get(key).stream()
                .map(dto -> dto.data.get(0))
                .toList();
        return WSActionDTO.builder()
                .data(history)
                .action("load_chat").build();
    }

    public WSActionDTO getUnreadMessage(String username, String target) {
        String key = username+"<->"+target;
        List<ReceiveChatDTO> history = chatHistory.get(key).stream()
                .filter(ws -> ws.action.equals("receive_chat"))
                .map(dto -> dto.data.get(0))
                .map(dto -> (ReceiveChatDTO) dto)
                .filter(chat -> chat.isNew)
                .toList();
        return WSActionDTO.builder()
                .action("count_unread")
                .data(history).build();
    }
}