package com.project.controllers.admin;

import com.project.models.Order;
import com.project.services.OrderService;
import com.project.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateMapDataServlet", urlPatterns = {"/api/update-mapdata"})
public class UpdateMapDataServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("content");
        // Đường dẫn tới file trên máy chủ
        String filePath = getServletContext().getRealPath("/WEB-INF/classes/map-data.json");

        // Ghi nội dung mới vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error writing to file: " + e.getMessage());
            return;
        }

        // ghi log cho việc cập nhật lượt truy cập này

    }
}