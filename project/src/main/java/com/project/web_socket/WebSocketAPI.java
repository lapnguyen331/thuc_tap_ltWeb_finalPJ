package com.project.web_socket;

import com.project.filters.RestResponseDTOApiFilter;
import com.project.service_rework.ProducerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "WebSocketAPI_v1", value = "/api/v1/web-socket/*")
public class WebSocketAPI extends HttpServlet {
    private ProducerService producerService;

    public WebSocketAPI() {
        this.producerService = new ProducerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String websocketUrl = "ws://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, websocketUrl);
    }
}
