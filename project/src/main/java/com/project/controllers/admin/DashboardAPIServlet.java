package com.project.controllers.admin;

import com.project.models.Contact;
import com.project.models.Order;
import com.project.services.ContactService;
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

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardAPIServlet", urlPatterns = {"/admin/dashboardAPI"})
public class DashboardAPIServlet extends HttpServlet {
    private UserService userService;
    private OrderService orderService;
    @Override
    public void init() throws ServletException {
        this.orderService = new OrderService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        loadDataCustomer(response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void loadDataCustomer(HttpServletResponse response){
        List<Order> orderList= orderService.getAllOrder();
        //tao json
        try {
            var out = response.getWriter();
            response.setContentType("application/json");

        JSONArray objectArray = new JSONArray();
        JSONObject json;
        for (Order u : orderList) {
            json = new JSONObject();
            json.put("id",u.getId());
            json.put("firstname",u.getUser().getFirstName());
            json.put("lastname",u.getUser().getLastName());
            json.put("createAt", u.getCreateAt());
            json.put("status",u.getStatus());
            json.put("total",u.getTotalPrice());
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