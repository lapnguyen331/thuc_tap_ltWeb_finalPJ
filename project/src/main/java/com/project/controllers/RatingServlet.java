package com.project.controllers;

import com.project.models.Rating;
import com.project.models.User;
import com.project.services.ProductService;
import com.project.services.RatingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "RatingServlet", urlPatterns = {"/rating"})
public class RatingServlet extends HttpServlet {
    private ProductService productService;
    private RatingService ratingService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.productService = new ProductService();
        this.ratingService = new RatingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("user");
        int id = Integer.parseInt(request.getParameter("product"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String content = request.getParameter("content");
        var product = productService.getById_short(id);
        ratingService.begin();
        int rs = ratingService.insertRating(new Rating(-1, user, product, rating, content, null, null));
        ratingService.commit();
        response.sendRedirect(getServletContext().getContextPath()+"/product?id="+id);
    }
}