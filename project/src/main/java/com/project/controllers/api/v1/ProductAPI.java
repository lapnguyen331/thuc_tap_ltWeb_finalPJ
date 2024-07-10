package com.project.controllers.api.v1;

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

    public ProductAPI() {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/getByCategory":
                doGetByCategory(request, response);
                break;
        }
    }

    private void doGetByCategory(HttpServletRequest request, HttpServletResponse response) {
        Integer categoryId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("api_response", productService.getProductCardOfCategory(categoryId));
    }
}
