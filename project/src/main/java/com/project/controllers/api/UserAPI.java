package com.project.controllers.api;

import com.project.models.Order;
import com.project.models.OrderItem;
import com.project.models.User;
import com.project.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserAPIServlet", value = "/api/customer/*")
public class UserAPI extends HttpServlet {
    private UserService userService;

    public UserAPI() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/getUser":
                doGetCustomer(request, response);
                break;
            case "/getAll":
                doGetAll(request, response);
                break;
        }
    }

    private void doGetAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var list = userService.getAll();
        response.setContentType("application/json");
        var out = response.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("data", new JSONArray());
        for (var user : list) {
            if (user.getLevelAccess() != 0) continue;
            obj.getJSONArray("data").put(getCustomerJSON(user));
        }
        out.print(obj);
        out.flush();
    }

    private void doGetCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        User user = userService.getInforById(customerId);
        response.setContentType("application/json");
        var out = response.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("data", new JSONArray());
        obj.getJSONArray("data").put(getCustomerJSON(user));
        out.print(obj);
        out.flush();
    }

    private JSONObject getCustomerJSON(User user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("name", user.getFirstName() + " " + user.getLastName());
        jsonObject.put("phone", user.getPhone());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("avatar", user.getAvatar().getPath());
        jsonObject.put("username", user.getUsername());
        return jsonObject;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}