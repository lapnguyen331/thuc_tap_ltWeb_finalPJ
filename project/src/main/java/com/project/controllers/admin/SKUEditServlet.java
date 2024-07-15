package com.project.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.dto.response.stock.SKURowDTO;
import com.project.service_rework.StockKeepingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        var session = request.getSession(false);
        List<Integer> stockIds = (List<Integer>) session.getAttribute("selectStockIDs");
        String json = objectMapper.writeValueAsString(stockIds);
        request.setAttribute("stockIdsJSON", stockIds);
        request.getRequestDispatcher("/WEB-INF/view/admin/sku_edit.jsp").forward(request, response);
    }
}
