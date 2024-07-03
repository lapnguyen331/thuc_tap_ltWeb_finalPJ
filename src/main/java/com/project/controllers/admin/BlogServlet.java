package com.project.controllers.admin;

import com.project.helpers.PropertiesFileHelper;
import com.project.models.Blog;
import com.project.models.Product;
import com.project.services.BlogService;
import com.project.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "AdminBlog", urlPatterns = {"/admin/blog"})
public class BlogServlet extends HttpServlet {
    private final BlogService blogService;
    public BlogServlet() {
        this.blogService = new BlogService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        var out = response.getWriter();
        if (action != null) {
            response.setContentType("text/plain");
            String json = null;
            switch (action) {
                case "getAll":
                {
                    json = getJSONBlogs(blogService.getAll());
                    System.out.println(json);
                    out.println(json);
                    out.flush();
                    break;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        var out = response.getWriter();
        String context = getUrl(request);
        if (action != null) {
            switch (action) {
                case "load":
                {
                    String path = request.getParameter("path");
                    String url = String.format("%s/files/%s", context, path);
                    String blog = blogService.readSavedBlog(url, request.getContextPath());
                    out.println(blog);
                    out.flush();
                    break;
                }
            }
        }
    }

    private String getJSONBlogs(List<Blog> blogs) {
        JSONArray data = new JSONArray();
        for (var blog : blogs) {
            JSONObject obj = new JSONObject();
            obj.put("id", blog.getId())
                    .put("title", blog.getTitle())
                    .put("image", blog.getThumbnail().getPath())
                    .put("path", blog.getPath());
            data.put(obj);
        }
        JSONObject rs = new JSONObject();
        rs.put("data", data);
        return rs.toString();
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