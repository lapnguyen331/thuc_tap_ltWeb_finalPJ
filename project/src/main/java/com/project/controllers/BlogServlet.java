package com.project.controllers;

import com.project.services.BlogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.project.models.Blog;

@WebServlet(name = "BlogServlet", urlPatterns = {"/blog"})
public class BlogServlet extends HttpServlet {
    private BlogService blogService;

    @Override
    public void init() throws ServletException {
        this.blogService = new BlogService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        List<Blog> blog = blogService.getAllBlog();
//        for(Blog e : blog){
//            Blog blogx = blogService.getBlogById(Integer.parseInt(e.getId()));
//            String context = getUrl(request);
//            String path = blogx.getPath();
//            String url = String.format("%s/files/%s", context, path);
//            String blogContent = blogService.readSavedBlog(url, request);
//
//        }

        request.setAttribute("resBlog",blog);

        request.setAttribute("blogList",blog);
        request.getRequestDispatcher("/WEB-INF/view/blog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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