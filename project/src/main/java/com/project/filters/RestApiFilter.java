package com.project.filters;

import com.google.gson.Gson;
import com.project.dto.MessageDTO;
import com.project.exceptions.custom_exception.MyServletException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "global3", urlPatterns = {"/api/v1/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class RestApiFilter implements Filter {
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
        Gson gson = new Gson();
        String str = null;
        int statusCode = 400;
        try {
            filterChain.doFilter(request, response);
            response.setContentType("json/application");
            str = gson.toJson(request.getAttribute("api_response"));
            statusCode = 200;
        } catch (MyServletException e) {
            if (e instanceof MyServletException) {
                var exception = (MyServletException) e;
                statusCode = exception.getErrorCode();
                str = gson.toJson(MessageDTO.builder().message(exception.getResponseMessage()));
            }
        };
        response.setStatus(statusCode);
        response.setContentType("json/application");
        response.getWriter().write(str);
        response.getWriter().flush();
    }
}