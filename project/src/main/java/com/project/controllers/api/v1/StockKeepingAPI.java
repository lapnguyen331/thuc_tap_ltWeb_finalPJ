package com.project.controllers.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.dto.MessageDTO;
import com.project.dto.request.stock.NewStockKeepingDTO;
import com.project.filters.RestResponseDTOApiFilter;
import com.project.filters.RestRequestBodyApiFilter;
import com.project.service_rework.ProductService;
import com.project.service_rework.StockKeepingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "StockKeeping_API", value = "/api/v1/stock-keeping/*")
public class StockKeepingAPI extends HttpServlet {
    private StockKeepingService stockKeepingService;

    public StockKeepingAPI() {
        this.stockKeepingService = new StockKeepingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getAttribute(RestRequestBodyApiFilter.PUT_KEY).toString();
        System.out.println("In StockKeeping Controller: "+json);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        NewStockKeepingDTO dto = objectMapper.readValue(json, NewStockKeepingDTO.class);
        stockKeepingService.begin();
        MessageDTO responseMessage = MessageDTO.builder().message("OK").build();
        if (stockKeepingService.insertNewStock(dto) > 0) {
            stockKeepingService.commit();
            req.setAttribute(RestResponseDTOApiFilter.PUT_KEY, responseMessage);
        }
    }

}
