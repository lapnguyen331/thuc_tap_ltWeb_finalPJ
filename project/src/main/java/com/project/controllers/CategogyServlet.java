package com.project.controllers;

import com.project.models.Category;
import com.project.models.Product;
import com.project.services.CategoryService;
import com.project.services.ImageService;
import com.project.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CategogyServlet", value = "/categogy")
public class CategogyServlet extends HttpServlet {
    private ProductService productService;
    private ImageService imageService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.productService = new ProductService();
        this.imageService = new ImageService();
        this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        List<Category> categories = categoryService.getAll_ID_name();
        Map<Integer, String> categoriesName = new HashMap<>();
        {
            categories.forEach(c -> categoriesName.put(c.getId(), c.getName()));
        }
        Map<Integer, List<Product>> productsCate = new HashMap<>();
        categories.forEach(c -> {
                    var em = productService.getALlOf(c);
                    productsCate.put(c.getId(), em);
        });
        System.out.println("hehe"+productsCate.toString());

        request.setAttribute("productsCate", productsCate);
        request.setAttribute("cateName", categoriesName);
        request.setAttribute("categories", categories);
        request.setAttribute("product3",productService.getTop4());
        request.setAttribute("brands", productService.getBrands());
        
        request.getServletContext().getRequestDispatcher("/WEB-INF/view/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}