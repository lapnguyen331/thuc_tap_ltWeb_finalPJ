package com.project.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.service_rework.StockKeepingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SKUEditServlet", urlPatterns = {"/admin/sku-edit"})
public class SKUEditServlet extends HttpServlet {
    private StockKeepingService stockKeepingService;
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.stockKeepingService = new StockKeepingService();
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        this.stockKeepingService.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());;
        var dto = stockKeepingService.getAllSKURowDTO();
        String json = objectMapper.writeValueAsString(dto);
        request.setAttribute("json", json);
        request.getRequestDispatcher("/WEB-INF/view/admin/sku_edit.jsp").forward(request, response);
    }
}
