package com.project.filters.rework;

import com.project.models.Cart;
import com.project.service_rework.CategoryService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "global2", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class UpdateSessionFilter implements Filter {
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
            session.setAttribute("categories", cService.getAll_id_name_thumbnail());
        }
        if (session.getAttribute("selectStockIDs") == null) {
            session.setAttribute("selectStockIDs", new ArrayList<Integer>());
        }
        var cart = session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}