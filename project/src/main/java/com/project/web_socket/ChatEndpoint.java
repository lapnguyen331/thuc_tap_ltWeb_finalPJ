package com.project.web_socket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.models.User;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.OnOpen;
import jakarta.websocket.*;
import jakarta.websocket.server.*;

import java.io.IOException;
import java.util.*;

@ServerEndpoint(value = "/chat/{target}", configurator = HttpSessionConfigurator.class)
public class ChatEndpoint {
    static Map<String, Set<Session>> map_user = new HashMap<>(); // user -> [logon sessions]
    static Map<String, Map<String, List<ChatDTO>>> chatHistory = new HashMap<>();

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
        var dto = ChatDTO.builder().message(message)
                        .actions(List.of("message"))
                        .build();
        session.getBasicRemote().sendText(mapper.writeValueAsString(dto));
        session.getUserProperties().put("username", username);
        if (chatHistory.get(username) == null) {
            chatHistory.put(username, new HashMap<>());
        }
        if (chatHistory.get(username).get(target) == null) {
            chatHistory.get(username).put(target, new ArrayList<>());
        }
        if (map_user.get(username) == null) {
            map_user.put(username, Collections.synchronizedSet(new HashSet<>()));
        }
        map_user.get(username).add(session);
        var history = chatHistory.get(username).get(target);
        String historyJSON = mapper.writeValueAsString(ChatDTO.builder()
                .history(history).actions(List.of("history"))
                .build());
        synchronizeData(username, historyJSON);
    }

    @OnMessage
    public void onMessage(Session session, @PathParam("target") String target, String message) throws IOException {
        // Đối với người gửi
        String username = session.getUserProperties().get("username").toString();
        ObjectMapper mapper = new ObjectMapper();
        var sender = ChatDTO.Sender.builder()
                .message(message)
                .username(username).build();
        String senderJSON = mapper.writeValueAsString(ChatDTO.builder()
                .sender(sender).actions(List.of("sender"))
                .build());
        synchronizeData(username, senderJSON);

        System.out.println("message: "+message);

        // Đối với người nhận
        var receiver = ChatDTO.Receiver.builder()
                .message(message)
                .username(username).build();
        String receiverJSON = mapper.writeValueAsString(ChatDTO.builder()
                .receiver(receiver).actions(List.of("receiver"))
                .build());
        synchronizeData(target, receiverJSON);
    }

    @OnClose
    public void onClose(Session session, @PathParam("target") String target) throws IOException {
        if (session.getUserProperties().get("username") == null) return;

        // Đối với người nhận
        String username = session.getUserProperties().get("username").toString();
        map_user.get(username).remove(session);
        if (map_user.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            var status = ChatDTO.Status.builder().isActive(false).build();
            String statusJSON = mapper.writeValueAsString(ChatDTO.builder()
                    .status(status).actions(List.of("change-status"))
                    .build());
            synchronizeData(target, statusJSON);
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
}