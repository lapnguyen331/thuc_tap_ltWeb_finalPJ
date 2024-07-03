package com.project.controllers;

import com.project.models.Rating;
import com.project.services.BlogService;
import com.project.services.ImageService;
import com.project.services.ProductService;
import com.project.services.RatingService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private ProductService productService;
    private ImageService imageService;
    private BlogService blogService;
    private RatingService ratingService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.productService = new ProductService();
        this.imageService = new ImageService();
        this.blogService = new BlogService();
        this.ratingService = new RatingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        var product = productService.getById(id);
        var galleries = imageService.getGalleriesOf(product);
        var related = productService.getTopOf(product.getCategory(), 4);
        List<Rating> comments = ratingService.getRatingOf(product);
        String context = getUrl(request);
        String path = product.getBlog().getPath();
        String url = String.format("%s/files/%s", context, path);
        String blog = blogService.readSavedBlog(url, request.getContextPath());
        request.setAttribute("blog", blog);
        request.setAttribute("product", product);
        request.setAttribute("galleries", galleries);
        request.setAttribute("related", related);
        request.setAttribute("comments", comments);
        request.getServletContext().getRequestDispatcher("/WEB-INF/view/details_product.jsp").forward(request, response);
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