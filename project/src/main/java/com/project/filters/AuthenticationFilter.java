package com.project.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Properties;

@WebFilter(filterName = "global1", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = httpRequest.getRequestURI();
        ServletContext context = request.getServletContext();
        HttpSession session = httpRequest.getSession(false);
//        Get resource name
        int lastIndex = uri.lastIndexOf("/");
        String resource = uri.substring(lastIndex + 1);
//        Lỗi xảy ra ở Google Chrome, chuyển hướng về trang khởi đầu..
//        if (resource.isEmpty()) {
//            ((HttpServletResponse) response).sendRedirect("home");
//            return;
//        }
//        Get Authentication Properties
        Properties authProperties = (Properties) context.getAttribute("AUTHENTICATION_LIST");
//        Check resource authentication
        String rule = authProperties.getProperty(resource);
//        System.out.printf("permission of %s = %s\n", httpRequest.getRequestURI(), rule);
        if (rule != null) {
            if ((session == null) || !(rule.equals(session.getAttribute("role")))) {
                httpRequest.getRequestDispatcher("login").forward(httpRequest, httpResponse);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}