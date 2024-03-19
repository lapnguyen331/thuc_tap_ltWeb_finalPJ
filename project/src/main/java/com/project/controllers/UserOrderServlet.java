package com.project.controllers;

import com.google.gson.Gson;
import com.project.models.Image;
import com.project.models.Order;
import com.project.models.User;
import com.project.services.OrderService;
import com.project.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@WebServlet(name = "UserOrderServlet", urlPatterns = {"/user-profile-order"})
public class UserOrderServlet extends HttpServlet {
    private UserService userService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        this.userService= new UserService();
        this.orderService = new OrderService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        User repUser = userService.getInforById(user.getId());
        if(repUser.getAvatar().getPath() == null){
            repUser.setAvatar(new Image(000,"/inventory/images/user-profile/use-avatar-header-default.jpg",null,null,null));
        }else{
            Image em = repUser.getAvatar();
            repUser.setAvatar(new Image(em.getId(),"/files/"+ em.getPath(),null,null,null));
        }
        request.setAttribute("userifor",repUser);
        request.getRequestDispatcher("/WEB-INF/view/user/user-profile-order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}