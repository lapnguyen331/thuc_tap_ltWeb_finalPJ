package com.project.controllers.rework;
import com.project.dto.response.category.CategoryIDNameDTO;
import com.project.dto.response.product.ProductCardDTO;
import com.project.service_rework.CategoryService;
import com.project.service_rework.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        this.productService = new ProductService();
        this.categoryService = new CategoryService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        List<CategoryIDNameDTO> categories = categoryService.getAll_id_name();
        Map<String, List<ProductCardDTO>> products1 = new HashMap<>();
        categories.forEach(c -> {
            String key = c.getName();
            var value = productService.getProductCardOfCategory(c.getId());
            products1.put(key, value);
        });
        request.setAttribute("products1", products1);
        request.setAttribute("products2", productService.getTop4());
        request.setAttribute("brands", List.of());
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}