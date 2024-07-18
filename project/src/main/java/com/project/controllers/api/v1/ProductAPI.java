package com.project.controllers.api.v1;

import com.project.filters.RestResponseDTOApiFilter;
import com.project.service_rework.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductAPI_v1", value = "/api/v1/products/*")
public class ProductAPI extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        productService = new ProductService();
        try {
            switch (action) {
                case "/getByCategory":
                    doGetByCategory(request, response);
                    break;
                case "/getDetails":
                    doGetDetails(request, response);
                    break;
                case "/getByName":
                    doGetByName(request, response);
                    break;
            }
        } catch(Exception e) {
            throw e;
        } finally {
            productService.close();
        }
    }

    private void doGetByName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        var list = productService.getProductLikeName(name);
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, list);
    }

    private void doGetDetails(HttpServletRequest request, HttpServletResponse response) {
        Integer productId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, productService.getDetailsByProductId(productId));
    }

    private void doGetByCategory(HttpServletRequest request, HttpServletResponse response) {
        Integer categoryId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, productService.getProductCardOfCategory(categoryId));
    }
}
