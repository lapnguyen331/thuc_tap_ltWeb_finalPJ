package com.project.controllers;

import com.project.models.Product;
import com.project.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "FilterServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        this.productService = new ProductService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int categoryId = Integer.parseInt(Optional.ofNullable(request.getParameter("category")).orElse("-1"));
        String brand = request.getParameter("brand");
        String[] range = Optional.ofNullable(request.getParameter("range"))
                .map(r -> r.split("-"))
                .orElse(null);
        System.out.println(Arrays.toString(range));
        var products = productService.search(name, categoryId, brand, (range == null) ? null : range[0], (range == null) ? null : range[1]);
        Map<Integer, List<Product>> mProducts = new HashMap<>();
        for (int num = 1, x = 0, i = 0; i < products.size(); i++) {
            if (num > 3) {
                num = 1;
                x++;
            }
            if (!mProducts.containsKey(x)) {
                var list = new ArrayList<Product>();
                mProducts.put(x, list);
            }
            mProducts.get(x).add(products.get(i));
            num++;
        }
        request.setAttribute("searchName", name);
        request.setAttribute("products", mProducts);
        request.getRequestDispatcher("/WEB-INF/view/search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}