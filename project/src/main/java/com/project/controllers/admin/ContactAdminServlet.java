package com.project.controllers.admin;

import com.project.models.Contact;
import com.project.models.Order;
import com.project.models_rework.log.Logger;
import com.project.services.ContactService;
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

@WebServlet(name = "ContactAdminServlet", urlPatterns = {"/admin/contactInform"})
public class ContactAdminServlet extends HttpServlet {
    private ContactService contactService;

    @Override
    public void init() throws ServletException {
        this.contactService = new ContactService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        int num = contactService.getUncheckNoti();
        //tao json
        try {
            var out = response.getWriter();
            response.setContentType("application/json");

            JSONArray objectArray = new JSONArray();
            JSONObject json;
            json = new JSONObject();
            json.put("uncheckNotiNum",num);
            objectArray.put(json);
            json = new JSONObject().put("data",objectArray);
            String data = json.toString();
//        System.out.println(data);
            out.write(data);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

//        request.getRequestDispatcher("/WEB-INF/view/admin/contacts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String firstname = request.getParameter("firstname");
//        String lastname = request.getParameter("lastname");
//        String email = request.getParameter("submitemail");
//        String phone = request.getParameter("submitphone");
//        String content = request.getParameter("content");
//        contactService.begin();
//        int rs = contactService.insertContact(new Contact(-1, firstname, lastname, phone, email, 0, content,null,null));
//        contactService.commit();
//        request.getRequestDispatcher("/WEB-INF/view/ThankYou.jsp").forward(request, response);

    }
}