package com.project.controllers.admin;

import com.project.models.Image;
import com.project.models.Order;
import com.project.models.User;
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

@WebServlet(name = "CustomerAPIServlet", urlPatterns = {"/admin/customerApi"})
public class CustomerAPIServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService= new UserService();
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
        List<User> userList= userService.getAllCustomer();
        //tao json
        try {
            var out = response.getWriter();
            response.setContentType("application/json");

        JSONArray objectArray = new JSONArray();
        JSONObject json;
        for (User u : userList) {
            json = new JSONObject();
            json.put("id",u.getId());
            json.put("name",u.getFullName(u.getFirstName(),u.getLastName()));
            json.put("email",u.getEmail());
            json.put("phone",u.getPhone());
            json.put("created",u.getCreateAt());
            json.put("status",u.getStatus());
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