package com.project.controllers;

import com.project.models.Order;
import com.project.models_rework.log.Logger;
import com.project.services.OrderService;
import com.project.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "IPServlet", urlPatterns = {"/track-ip"})
public class IPServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Lấy địa chỉ IP của người dùng
        String userIP = request.getRemoteAddr();
        String secretKey ="e560dcae828b4c5289260dab0abcfe6b";


        // Ghi IP vào cơ sở dữ liệu hoặc ghi log
//        logUserIP(userIP);
        try {
            // Tạo yêu cầu GET tới URL
            String url = "https://api.geoapify.com/v1/ipinfo?&apiKey=" + secretKey;
            Content content = Request.get(url)
                    .execute()
                    .returnContent();
            // In nội dung phản hồi
            System.out.println(content.asString());
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("ip tracking service not working");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//    protected void loadDataCustomer(HttpServletResponse response){
//        List<Order> orderList= orderService.getAllOrder();
//        //tao json
//        try {
//            var out = response.getWriter();
//            response.setContentType("application/json");
//
//        JSONArray objectArray = new JSONArray();
//        JSONObject json;
//        for (Order u : orderList) {
//            json = new JSONObject();
//            json.put("id",u.getId());
//            json.put("firstname",u.getUser().getFirstName());
//            json.put("lastname",u.getUser().getLastName());
//            json.put("createAt", u.getCreateAt());
//            json.put("status",u.getStatus());
//            json.put("total",u.getTotalPrice());
////            json.put("status",u.getStatus()==1 ? "active":"deactive");
////            json.put("note",u.getContent());
//            objectArray.put(json);
////            order.getDateOnly(order.getCreateAt())
//        }
//        json = new JSONObject().put("data",objectArray);
//        String data = json.toString();
////        System.out.println(data);
//            out.write(data);
//            out.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}