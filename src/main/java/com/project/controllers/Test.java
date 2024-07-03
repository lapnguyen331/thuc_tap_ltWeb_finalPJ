package com.project.controllers;

import com.project.models.Order;
import com.project.models.User;
import com.project.services.OrderService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        User em = new User();
        em.setId(1);
        List<Order> orderList= orderService.getAllOrderOfUser(em);
//        //tao json
//        try {
//            var out = response.getWriter();
//            response.setContentType("application/json");

            JSONArray objectArray = new JSONArray();
            JSONObject json;
            for (Order order : orderList) {
                json = new JSONObject();
                json.put("id",order.getId());
                json.put("totalprice",order.getTotalPrice());
                String status ="";
                switch (order.getStatus()) {
                    case 0:
                        status="đã bị hủy";
                        break;
                    case 1:
                        status="đã được giao";
                        break;
                    case 2:
                        status="đang giao";
                        break;
                    case 3:
                        status="đang trả về";
                        break;
                }
                json.put("status",status);
//                json.put("create",order.getDateOnly(order.getCreateAt()));
                objectArray.put(json);
            }
            json = new JSONObject().put("data",objectArray);
            String data = json.toString();
            System.out.println(data);
//            out.write(data);
//            out.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
