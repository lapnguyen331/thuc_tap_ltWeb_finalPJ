package com.project.controllers.api.v1;

import com.project.filters.RestResponseDTOApiFilter;
import com.project.service_rework.ProducerService;
import com.project.service_rework.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProducerAPI_v1", value = "/api/v1/producer/*")
public class ProducerAPI extends HttpServlet {
    private ProducerService producerService;

    public ProducerAPI() {
        this.producerService = new ProducerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/getAll_producerIDName":
                doGetAll_producerIDName(request, response);
                break;
        }
    }

    private void doGetAll_producerIDName(HttpServletRequest request, HttpServletResponse response) {
        var list = producerService.getAllProducerIDName();
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, list);
    }
}
