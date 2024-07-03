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
import java.util.List;

@WebServlet(name = "CategogyDetailServlet", value = "/categogy-detail")
public class CategogyDetailServlet extends HttpServlet {
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
        int id = Integer.parseInt(request.getParameter("id"));
        var categogyList = categoryService.getAll();
        Category cate = categoryService.findCateById(id);
        List<Category> categories = categoryService.getAll_ID_name();
        List<Product> productList = productService.getAllProductOffCate(cate);
        String context = getUrl(request);
//        String path =
//        String url = String.format("%s/files/%s", context, path);
        request.setAttribute("productCate",productList);
        request.setAttribute("productTop",productService.getTopOf(cate, 4));
        request.setAttribute("categories", categories);
        request.setAttribute("brands", productService.getBrands());
        request.setAttribute("cateItem",cate);


        request.getServletContext().getRequestDispatcher("/WEB-INF/view/categogy-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String getUrl(HttpServletRequest request) {
        String scheme = request.getScheme();             // http / https
        String serverName = request.getServerName();     // hostname.com
        int serverPort = request.getServerPort();        // 80
        String contextPath = request.getContextPath();   // /app

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://");
        url.append(serverName);

        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        return url.toString();
    }
}