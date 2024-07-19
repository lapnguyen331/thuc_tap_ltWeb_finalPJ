package com.project.controllers.admin;

import com.project.models_rework.log.Log;
import com.project.service_rework.LogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogAPIServlet", urlPatterns = {"/admin/logAPI"})
public class LogAPIServlet extends HttpServlet {
    private LogService logService;
    @Override
    public void init() throws ServletException {
        this.logService= new LogService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        loadDataLog(response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            int rowsAffected = logService.deleteById(id);

            if (rowsAffected > 0) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Log not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid log ID");
        }
    }
    protected void loadDataLog(HttpServletResponse response){
        List<Log> logList= logService.getAll();
        //tao json
        try {
            var out = response.getWriter();
            response.setContentType("application/json");

        JSONArray objectArray = new JSONArray();
        JSONObject json;
        for (Log u : logList) {
            json = new JSONObject();
            json.put("id",u.getId());
            json.put("level",u.getLevel());
            json.put("message",u.getMessage());
            json.put("createAt", u.getLogTime());
            json.put("ipAddress",u.getIpAddress());
//            json.put("status",u.getStatus()==1 ? "active":"deactive");
//            json.put("note",u.getContent());
            objectArray.put(json);
//            order.getDateOnly(order.getCreateAt())
        }
        json = new JSONObject().put("data",objectArray);
        String data = json.toString();
//        System.out.println(data);
            out.write(data);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}