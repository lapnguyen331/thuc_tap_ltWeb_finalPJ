package com.project.filters;

import com.google.gson.Gson;
import com.project.dto.MessageDTO;
import com.project.exceptions.custom_exception.MyServletException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

@WebFilter(filterName = "global4", urlPatterns = {"/api/v1/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class RestRequestBodyApiFilter implements Filter {
    public static final String PUT_KEY = "json_data";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;
        if (request.getMethod().equalsIgnoreCase("POST") || request.getMethod().equalsIgnoreCase("PUT")) {
            String requestData = request.getReader().lines().collect(Collectors.joining());
            System.out.println("In Filter: "+requestData);
            request.setAttribute("json_data", requestData);
        }
        filterChain.doFilter(request, response);
    }
}