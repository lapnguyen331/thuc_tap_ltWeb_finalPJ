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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserOrderAPIServlet", urlPatterns = {"/api/user-profile-orderapi"})
public class UserOrderAPIServlet extends HttpServlet {
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
        if(repUser.getAvatar().getPath().isEmpty()){
            repUser.setAvatar(new Image(000,"/inventory/images/user-profile/use-avatar-header-default.jpg",null,null,null));
        }else{
            Image em = repUser.getAvatar();
            repUser.setAvatar(new Image(em.getId(),"/files/"+ em.getPath(),null,null,null));
        }
        loadDataOrder(repUser,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void loadDataOrder(User repUser,HttpServletResponse response){
        List<Order> orderList= orderService.getAllOrderOfUser(repUser);
        //tao json
        try {
            var out = response.getWriter();
            response.setContentType("application/json");

        JSONArray objectArray = new JSONArray();
        JSONObject json;
        for (Order order : orderList) {
            json = new JSONObject();
            json.put("id",order.getId());
            json.put("totalprice",order.getTotalPrice());

            json.put("status",order.getStatus());
            json.put("create",order.getCreateAt1());
            objectArray.put(json);
//            order.getDateOnly(order.getCreateAt())
        }
        json = new JSONObject().put("data",objectArray);
        String data = json.toString();
//        System.out.println(data);
            out.write(data);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}