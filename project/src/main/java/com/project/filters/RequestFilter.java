package com.project.filters;

import com.project.models.Cart;
import com.project.models.User;
import com.project.services.CategoryService;
import com.project.services.UserService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "global2", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class RequestFilter implements Filter {
    CategoryService cService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.cService = new CategoryService();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession(true);
        if (session.getAttribute("categories") == null) {
            session.setAttribute("categories", cService.getAll());
        }
        var cart = session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}