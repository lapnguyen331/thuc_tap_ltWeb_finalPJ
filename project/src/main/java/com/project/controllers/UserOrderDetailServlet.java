package com.project.controllers;

import com.project.models.Image;
import com.project.models.Order;
import com.project.models.OrderItem;
import com.project.models.User;
import com.project.services.OrderItemService;
import com.project.services.OrderService;
import com.project.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserOrderDetailServlet", urlPatterns = {"/user-profile-order-detail"})
public class UserOrderDetailServlet extends HttpServlet {
    private UserService userService;
    private OrderItemService orderItemService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        this.userService= new UserService();
        this.orderService= new OrderService();
        this.orderItemService = new OrderItemService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
//        doPost(request,response);
        User user = (User) session.getAttribute("user");
        User repUser = userService.getInforById(user.getId());
        if(repUser.getAvatar().getPath().isEmpty()){
            repUser.setAvatar(new Image(000,"/inventory/images/user-profile/use-avatar-header-default.jpg",null,null,null));
        }else{
            Image em = repUser.getAvatar();
            repUser.setAvatar(new Image(em.getId(),"/files/"+ em.getPath(),null,null,null));
        }
        String orderitemid = request.getParameter("orderdes");
        Order or  =  orderService.getOrderById(Integer.parseInt(orderitemid));
//        orderService.
        List<OrderItem> item = orderItemService.getAll();
        List<OrderItem> resitem = new ArrayList<>();
        for(OrderItem em :item){
            if(em.getOrder().getId() == Integer.parseInt(orderitemid)){
                resitem.add(em);
            }
        }
        request.setAttribute("currorder",or);
        request.setAttribute("resitem",resitem);
        resitem.forEach(System.out::println);
        request.setAttribute("userifor",repUser);
        request.getRequestDispatcher("/WEB-INF/view/user/user-profile-order-detail.jsp").forward(request, response);

//        request.getRequestDispatcher("/WEB-INF/view/user/user-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}