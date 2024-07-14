package com.project.controllers.api.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.dto.MessageDTO;
import com.project.dto.request.stock.ChangeInStockDTO;
import com.project.dto.request.stock.NewStockKeepingDTO;
import com.project.dto.request.stock.data_table.DataTableFilterDTO;
import com.project.dto.response.dataTable.DataTableDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.filters.RestResponseDTOApiFilter;
import com.project.filters.RestRequestBodyApiFilter;
import com.project.service_rework.StockKeepingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "StockKeeping_API", value = "/api/v1/stock-keeping/*")
public class StockKeepingAPI extends HttpServlet {
    private StockKeepingService stockKeepingService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        stockKeepingService = new StockKeepingService();
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        stockKeepingService.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void doGet_SKURowData(HttpServletRequest request, HttpServletResponse response) throws MyServletException {
        Integer stockId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, stockKeepingService.getSKURowDTO(stockId));
    }

    private void doGet_AllSKURowData(HttpServletRequest request, HttpServletResponse response) throws MyServletException {
        var list = stockKeepingService.getAllSKURowDTO();
        var dto = DataTableDTO.builder()
                .data(list).build();
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, dto);
    }

    private void doGet_SKUHistoryData(HttpServletRequest request, HttpServletResponse response) throws MyServletException {
        Integer stockId = Integer.parseInt(request.getParameter("id"));
        Integer month = Integer.parseInt(request.getParameter("month"));
        var list = stockKeepingService.getStockHistoryInMonthOfStockKeeping(stockId, month);
        var dto = DataTableDTO.builder()
                .data(list).build();
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, dto);
    }


    private void doGetAll_SKUHistoryData(HttpServletRequest request, HttpServletResponse response) throws MyServletException, JsonProcessingException {
        String json = request.getAttribute(RestRequestBodyApiFilter.PUT_KEY).toString();
        ObjectMapper mapper = new ObjectMapper();
        DataTableFilterDTO filterDTO = mapper.readValue(json, DataTableFilterDTO.class);
        var dto = stockKeepingService.getAllSKUHistory(filterDTO);
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, dto);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        synchronized (stockKeepingService) {
            switch (action) {
                case "/doGet-SKURowData": {
                    doGet_SKURowData(request, response);
                    break;
                }
                case "/doGetAll-SKURowData": {
                    doGet_AllSKURowData(request, response);
                    break;
                }
                case "/doGet-SKUHistoryData": {
                    doGet_SKUHistoryData(request, response);
                    break;
                }
                case "/doGetAll-SKUHistoryData": {
                    doGetAll_SKUHistoryData(request, response);
                    break;
                }
                case "/doAddNew-SKU": {
                    doAddNew_SKU(request, response);
                    break;
                }
            }
        }
    }

    private void doAddNew_SKU(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getAttribute(RestRequestBodyApiFilter.PUT_KEY).toString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        NewStockKeepingDTO dto = objectMapper.readValue(json, NewStockKeepingDTO.class);
        stockKeepingService.begin();
        MessageDTO responseMessage = MessageDTO.builder().message("OK").build();
        try {
            if (stockKeepingService.insertNewStock(dto) > 0) {
                stockKeepingService.commit();
                request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, responseMessage);
            }
        } catch (Exception e) {
            stockKeepingService.rollback();
            throw e;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getAttribute(RestRequestBodyApiFilter.PUT_KEY).toString();
        ObjectMapper objectMapper = new ObjectMapper();
        ChangeInStockDTO dto = objectMapper.readValue(json, ChangeInStockDTO.class);
        stockKeepingService.begin();
        MessageDTO responseMessage = MessageDTO.builder().message("OK").build();
        try {
            if (stockKeepingService.changeInStock(dto) > 0) {
                stockKeepingService.commit();
                req.setAttribute(RestResponseDTOApiFilter.PUT_KEY, responseMessage);
            }
        } catch (Exception e) {
            stockKeepingService.rollback();
            throw e;
        }
    }
}
