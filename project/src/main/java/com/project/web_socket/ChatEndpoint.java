package com.project.web_socket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.dto.response.chat.UserChatRowDTO;
import com.project.models.User;
import com.project.service_rework.ChatService;
import com.project.service_rework.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.OnOpen;
import jakarta.websocket.*;
import jakarta.websocket.server.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@ServerEndpoint(value = "/chat", configurator = HttpSessionConfigurator.class)
public class ChatEndpoint {
    private ChatService service = new ChatService();
    static Map<String, Set<Session>> map_user = new HashMap<>(); // user -> [logon sessions]
    static Map<Integer, Set<Session>> map_user_v2 = new HashMap<>(); // userId -> [logon sessions]
    static Map<String, List<WSActionDTO>> chatHistory = new HashMap<>();
    // username -> targetname
    private ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @OnOpen
    public void onOpen(Session session) throws IOException {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
        if (httpSession.getAttribute("user") == null) {
            var dto = createMessage("Bạn chưa đăng nhập, đóng WebSocket này");
            session.getBasicRemote().sendText(mapper.writeValueAsString(dto));
            session.close();
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        User user = (User) httpSession.getAttribute("user");
        String username = user.getUsername();
        Integer userId = user.getId();
        String message = "Bạn đã đăng nhập với tư cách user: "+username;
        WSActionDTO dto = null;
        dto = createMessage(message);
        session.getBasicRemote().sendText(mapper.writeValueAsString(dto));
        session.getUserProperties().put("username", username);
        session.getUserProperties().put("userId", userId);
        if (map_user.get(username) == null) {
            map_user.put(username, Collections.synchronizedSet(new HashSet<>()));
        }
        if (map_user_v2.get(userId) == null) {
            map_user_v2.put(userId, Collections.synchronizedSet(new HashSet<>()));
        }
        map_user.get(username).add(session);
        map_user_v2.get(userId).add(session);
    }

    @OnMessage
    public void onMessage(Session session, String requestJSON) throws IOException {
        WSUserRequestDTO requestDTO = mapper.readValue(requestJSON, WSUserRequestDTO.class);
        String username = session.getUserProperties().get("username").toString();
        if (chatHistory.get(username+"<->"+requestDTO.target) == null) {
            chatHistory.put(username+"<->"+requestDTO.target, new ArrayList<>());
        }
        if (chatHistory.get(requestDTO.target+"<->"+username) == null) {
            chatHistory.put(requestDTO.target+"<->"+username, new ArrayList<>());
        }

        switch (requestDTO.action) {
            case "send_chat": {
                Integer userId = (Integer) session.getUserProperties().get("userId");
                Integer targetId = (Integer) session.getUserProperties().get("targetId");

                // Đối với người gửi
                var sendChat = sendChat(userId, targetId, requestDTO.data);
                synchronizeData(userId, mapper.writeValueAsString(sendChat));

                // Đối với người nhận
                var receiveChat = receiveChat(userId, targetId, requestDTO.data);
                synchronizeData(targetId, mapper.writeValueAsString(receiveChat));
                break;
            }
            case "get_history": {
                Integer targetId = (Integer) session.getUserProperties().get("targetId");
                Integer userId = (Integer) session.getUserProperties().get("userId");
                var dto = getChatBetween(userId, targetId);
                session.getBasicRemote().sendText(mapper.writeValueAsString(dto));
                break;
            }
            case "count_unread": {
                var unreadMsg = getUnreadMessage(username, requestDTO.target);
                synchronizeData(username, mapper.writeValueAsString(unreadMsg));
                break;
            }
            case "open_chat_window_with": {
                session.getUserProperties().put("targetId", Integer.parseInt(requestDTO.data));
                var response = createMessage("OK");
                session.getBasicRemote().sendText(mapper.writeValueAsString(response));
                break;
            }
            case "mark_read": {
                Integer targetId = (Integer) session.getUserProperties().get("targetId");
                Integer userId = (Integer) session.getUserProperties().get("userId");
                var dto = markRead(userId, targetId);
                synchronizeData(userId, mapper.writeValueAsString(dto));
                break;
            }
            case "get_last_chat": {
                Integer id = (Integer) session.getUserProperties().get("userId");
                var dtos = getLastChats(id);
                session.getBasicRemote().sendText(mapper.writeValueAsString(dtos));
                break;
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String username = (String) session.getUserProperties().get("username");
        Integer userId = (Integer) session.getUserProperties().get("userId");
        if (map_user.get(username) != null) {
            map_user.get(username).remove(session);
        }
        if (map_user_v2.get(userId) != null) {
            map_user_v2.get(userId).remove(session);
        }
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

    public static void synchronizeData(Integer userId, String data) throws IOException {
        if (map_user_v2.get(userId) != null) {
            for (var session : map_user_v2.get(userId)) {
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

    public WSActionDTO markRead(Integer id1, Integer id2) {
        return WSActionDTO.builder()
                .action("mark_read")
                .data(List.of(id2))
                .build();
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
                .data(List.of(history.size()))
                .build();
    }

    public WSActionDTO getLastChats(Integer userId) {
        var dto = service.getChatRowOfUserId(userId);
        return WSActionDTO.builder()
                .action("get_last_chat")
                .data(dto)
                .build();
    }

    public WSActionDTO getChatBetween(Integer id1, Integer id2) {
        var dto = service.getChatsBetween(id1, id2);
        return WSActionDTO.builder()
                .action("get_history")
                .data(dto)
                .build();
    }

    public WSActionDTO sendChat(Integer id1, Integer id2, String message) {
//        var dto = service.getChatsBetween(id1, id2);
        var sender = new UserChatRowDTO.Sender().builder().id(id1).build();
        var receiver = new UserChatRowDTO.Receiver().builder().id(id2).build();
        var dto = UserChatRowDTO.builder()
                .createAt(LocalDateTime.now())
                .sender(sender).receiver(receiver)
                .isNew(true).message(message).build();
        return WSActionDTO.builder()
                .action("send_chat")
                .data(List.of(dto))
                .build();
    }

    public WSActionDTO receiveChat(Integer id1, Integer id2, String message) {
//        var dto = service.getChatsBetween(id1, id2);
        var sender = new UserChatRowDTO.Sender().builder().id(id1).build();
        var receiver = new UserChatRowDTO.Receiver().builder().id(id2).build();
        var dto = UserChatRowDTO.builder()
                .createAt(LocalDateTime.now())
                .sender(sender).receiver(receiver)
                .isNew(true).message(message).build();
        return WSActionDTO.builder()
                .action("receive_chat")
                .data(List.of(dto))
                .build();
    }
}