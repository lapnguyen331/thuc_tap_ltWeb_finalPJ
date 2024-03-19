package com.project.controllers;

import com.project.models.Blog;
import com.project.services.BlogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "BlogDetailServlet", urlPatterns = {"/blogDetail"})
public class BlogDetailServlet extends HttpServlet {
    private BlogService blogService;

    @Override
    public void init() throws ServletException {
        this.blogService= new BlogService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String blogid = request.getParameter("id");
        System.out.println(blogid);
        Blog blog = blogService.getBlogById(Integer.parseInt(blogid));
        String context = getUrl(request);
        String path = blog.getPath();
        String url = String.format("%s/files/%s", context, path);
        String blogContent = blogService.readSavedBlog(url, request);
        request.setAttribute("resBlog",blog);
        request.setAttribute("blogcontent",blogContent);
        System.out.println(blogContent);
        request.getRequestDispatcher("/WEB-INF/view/blog-detail.jsp").forward(request, response);
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