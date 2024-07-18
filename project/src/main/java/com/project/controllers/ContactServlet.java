package com.project.controllers;

import com.project.dao.IContactDAO;
import com.project.dao.implement.ContactDAO;
import com.project.models.Contact;
import com.project.models.Rating;
import com.project.services.ContactService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ContactADServlet", urlPatterns = {"/contact"})
public class ContactServlet extends HttpServlet {
    private ContactService contactService;

    @Override
    public void init() throws ServletException {
        this.contactService = new ContactService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        request.getRequestDispatcher("/WEB-INF/view/contact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("submitemail");
        String phone = request.getParameter("submitphone");
        String content = request.getParameter("content");
        contactService.begin();
        int rs = contactService.insertContact(new Contact(-1, firstname, lastname, phone, email, 0, content,null,null));
        contactService.commit();
        request.getRequestDispatcher("/WEB-INF/view/ThankYou.jsp").forward(request, response);

    }
}