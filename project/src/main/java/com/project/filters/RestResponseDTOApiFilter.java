package com.project.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.project.dto.MessageDTO;
import com.project.exceptions.custom_exception.MyServletException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "global3", urlPatterns = {"/api/v1/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class RestResponseDTOApiFilter implements Filter {
    public static final String PUT_KEY = "api_response";
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
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        response.addHeader("Access-Control-Allow-Origin", "*");
        String str = null;
        int statusCode = 400;
        try {
            filterChain.doFilter(request, response);
            response.setContentType("json/application");
            str = objectMapper.writeValueAsString(request.getAttribute(PUT_KEY));
            statusCode = 200;
        } catch (MyServletException e) {
            if (e instanceof MyServletException) {
                var exception = (MyServletException) e;
                statusCode = exception.getErrorCode();
                str = objectMapper.writeValueAsString(MessageDTO.builder().message(exception.getResponseMessage()).build());
            }
        };
        response.setStatus(statusCode);
        response.setContentType("json/application");
        response.getWriter().write(str);
        response.getWriter().flush();
    }
}