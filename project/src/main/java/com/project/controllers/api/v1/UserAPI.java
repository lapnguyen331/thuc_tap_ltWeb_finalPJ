package com.project.controllers.api.v1;

import com.project.exceptions.custom_exception.MyServletException;
import com.project.filters.RestResponseDTOApiFilter;
import com.project.service_rework.ProductService;
import com.project.service_rework.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UserAPI_v1", value = "/api/v1/users/*")
public class UserAPI extends HttpServlet {
    private UserService userService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (userService == null)
            userService = new UserService();
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        userService.close();
        userService = null;
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/getLikeUsername":
                    doGetLikeUsername(request, response);
                    break;
            }
        } catch(Exception e) {
            throw e;
        }
    }

    private void doGetLikeUsername(HttpServletRequest request, HttpServletResponse response) throws MyServletException {
        String username = request.getParameter("username");
        var dto = userService.getUsersByUsername(username);
        System.out.println(dto);
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, dto);
    }
}
