package com.project.controllers;

import com.project.exceptions.NotEnoughQuantityException;
import com.project.exceptions.NotFoundProductException;
import com.project.models.*;
import com.project.services.OrderService;
import com.project.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "SearchServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    private OrderService orderService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        this.orderService = new OrderService();
        this.productService = new ProductService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession(false).getAttribute("cart");
        request.setAttribute("items", cart.getProducts().values().stream().toList());
        request.getRequestDispatcher("/WEB-INF/view/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String receiverFirstName = request.getParameter("receiverFirstName");
        String receiverLastName = request.getParameter("receiverLastName");
        String receiverPhone = request.getParameter("receiverPhone");
        String receiverEmail = request.getParameter("receiverEmail");
        User user = (User) request.getSession(false).getAttribute("user");
        Cart cart = (Cart) request.getSession(false).getAttribute("cart");
        Order order = new Order(-1, user, 0f, 1, receiverFirstName, receiverLastName, receiverPhone, receiverEmail, null, null);
        String responseMessage = "Đặt đơn hàng thành công!";
        try {
            orderService.begin();
            orderService.createOrder(cart.getProducts().values().stream().toList(), order);
            cart.getProducts().clear();
            orderService.commit();
        } catch (NotEnoughQuantityException | NotFoundProductException e) {
            orderService.rollback();
            responseMessage = e.getMessage();
        }
        request.setAttribute("result", responseMessage);
        request.getRequestDispatcher("/cart").include(request, response);
    }
}