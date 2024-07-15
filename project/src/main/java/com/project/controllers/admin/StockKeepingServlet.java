package com.project.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.service_rework.StockKeepingService;
import com.project.services.ContactService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StockKeepingServlet", urlPatterns = {"/admin/stock-keeping"})
public class StockKeepingServlet extends HttpServlet {
    private StockKeepingService service;
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service = new StockKeepingService();
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        this.service.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        var session = request.getSession(false);
        List<Integer> stockIds = (List<Integer>) session.getAttribute("selectStockIDs");
        String json = objectMapper.writeValueAsString(stockIds);
        request.setAttribute("json", json);
        request.getRequestDispatcher("/WEB-INF/view/admin/stock_keeping.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());;
        var dto = service.getAllSKURowDTO();
        String json = objectMapper.writeValueAsString(dto);
        request.setAttribute("json", json);
        request.getRequestDispatcher("/WEB-INF/view/admin/sku_edit.jsp").forward(request, response);
    }
}
