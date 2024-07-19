package com.project.controllers.admin;

import com.project.models.Contact;
import com.project.models.User;
import com.project.models_rework.log.Logger;
import com.project.services.ContactService;
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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@WebServlet(name = "ContactAPIServlet", urlPatterns = {"/admin/contactAPI"})
public class ContactAPIServlet extends HttpServlet {
    private ContactService contactService;

    @Override
    public void init() throws ServletException {
        this.contactService= new ContactService();
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
        List<Contact> contactList= contactService.getALL();
        //tao json
        try {
            var out = response.getWriter();
            response.setContentType("application/json");

        JSONArray objectArray = new JSONArray();
        JSONObject json;
        for (Contact u : contactList) {
            json = new JSONObject();
            json.put("id",u.getId());
            json.put("firstname",u.getFirstName());
            json.put("lastname",u.getLastName());
            json.put("phone",u.getPhone());
            json.put("email",u.getEmail());
            json.put("created",u.getCreateAt());
            json.put("status",u.getStatus()==1 ? "active":"deactive");
            json.put("note",u.getContent());
            objectArray.put(json);
//            order.getDateOnly(order.getCreateAt())
        }
        json = new JSONObject().put("data",objectArray);
        String data = json.toString();
//        System.out.println(data);
            out.write(data);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}