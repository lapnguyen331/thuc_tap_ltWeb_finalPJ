package com.project.web_socket.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.models.Cart;
import com.project.models.User;
import com.project.service_rework.UploadService;
import com.project.web_socket.HttpSessionConfigurator;
import com.project.web_socket.WSActionDTO;
import com.project.web_socket.WSUserRequestDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@ServerEndpoint(value = "/cart", configurator = HttpSessionConfigurator.class)
public class CartEndPoint {
    static Map<Integer, Cart> map_cart = new HashMap<>();
    static Map<Integer, Set<Session>> map_user = new HashMap<>();
    private ObjectMapper mapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
        if (httpSession.getAttribute("user") == null) {
            httpSession.setAttribute("cart", new Cart());
            session.close();
            return;
        }
        User user = (User) httpSession.getAttribute("user");
        String username = user.getUsername();
        Integer userId = user.getId();

        if (map_cart.get(userId) == null) {
            map_cart.put(userId, new Cart());
        }
        httpSession.setAttribute("cart", map_cart.get(userId));
        String message = "Đã áp dụng dữ liệu giỏ hàng cho user: " + username;
        session.getBasicRemote().sendText(mapper.writeValueAsString(message));
        session.getUserProperties().put("userId", userId);
        if (map_user.get(userId) == null) {
            map_user.put(userId, Collections.synchronizedSet(new HashSet<>()));
        }
        map_user.get(userId).add(session);
    }

    @OnMessage
    public void onMessage(Session session, String requestJSON) throws IOException {
        WSUserRequestDTO requestDTO = mapper.readValue(requestJSON, WSUserRequestDTO.class);
        Integer id = (Integer) session.getUserProperties().get("userId");
        switch (requestDTO.action) {
            case "sync-change": {
                var dto = getCartJSON(id);
                synchronizeData(id, mapper.writeValueAsString(dto));
                break;
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        Integer userId = (Integer) session.getUserProperties().get("userId");
        if (map_user.get(userId) != null) {
            map_user.get(userId).remove(session);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    public WSActionDTO getCartJSON(Integer userId) {
        var cart = map_cart.get(userId);
        var set = cart.getProducts().entrySet();
        JSONArray arr = new JSONArray();
        JSONObject obj;
        for (var entry : set) {
            var value = entry.getValue();
            String path = value.getProduct().getThumbnail().getPath();
            var uploadService = new UploadService();
            try {
                String link = uploadService.getURL(path);
                value.getProduct().getThumbnail().setPath(link);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            obj = new JSONObject();
            obj.put("id", entry.getKey());
            obj.put("item", JSONObject.wrap(entry.getValue()));
            arr.put(obj);
        }
        System.out.println(arr);
        return WSActionDTO.builder()
                .action("sync-change")
                .data(List.of(arr))
                .build();
    }

    public static void synchronizeData(Integer userId, String data) throws IOException {
        if (map_user.get(userId) != null) {
            for (var session : map_user.get(userId)) {
                session.getBasicRemote().sendText(data);
            }
        }
    }
}
