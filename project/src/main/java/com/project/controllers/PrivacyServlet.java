package com.project.controllers;

import com.project.services.CategoryService;
import com.project.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "PrivacyServlet", urlPatterns = {"/privacy"})
public class PrivacyServlet extends HttpServlet {
    private ProductService productService;
    @Override
    public void init() throws ServletException {
        this.productService = new ProductService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        request.setAttribute("products2", productService.getTopNum(3)); //lấy top 3 sp hiển thị
        request.getRequestDispatcher("/WEB-INF/view/privacy.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}