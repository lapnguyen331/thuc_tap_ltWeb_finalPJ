package com.project.controllers.admin;

import com.project.models.Image;
import com.project.models.User;
import com.project.services.ContactService;
import com.project.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.transaction.TransactionalException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@WebServlet(name = "CustomerEditServlet", urlPatterns = {"/admin/customers_edit"})
public class CustomerEditServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("customerid");
        User user = userService.getInforById(Integer.parseInt(id));
        request.setAttribute("repuser",user);
        request.getRequestDispatcher("/WEB-INF/view/admin/customers_edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String currid = request.getParameter("userid");
        String status = request.getParameter("userstatusvalue");
        String firstName = request.getParameter("userfirstname");
        String lastName = request.getParameter("userlastname");
        String phone = request.getParameter("userphone");
        String email = request.getParameter("useremail");
        String address = request.getParameter("useraddress");
        String birth = request.getParameter("userbirth");
        System.out.println("hehe"+firstName);

        User repUser1 = userService.getInforById(Integer.parseInt(currid));

        String responseMessage = "cập nhật email thành công!";
        try {
            userService.begin();
            int cId = repUser1.getId();
            var cUsername = repUser1.getUsername();
            var cFirstName = getStr(firstName, repUser1.getFirstName());
            var cLastName = getStr(lastName, repUser1.getLastName());
            var cEmail = getStr(email, repUser1.getEmail());
            var cPhone = getStr(phone, repUser1.getPhone());
            var cAddress = getStr(address, repUser1.getAddress());
            var cGender = repUser1.conGender(repUser1.isGender());
            var cBirth = String.valueOf(repUser1.getBirthDate());
            var cStatus = getStr(status, repUser1.getStatus()+"");
            userService.updateCustomerInfor(cId, cUsername, cFirstName, cLastName, cEmail,
                    cPhone, cAddress, cGender, cBirth, cStatus);
            userService.commit();
            repUser1.setFirstName(firstName);
            repUser1.setEmail(email);
            repUser1.setLastName(lastName);
            repUser1.setAddress(address);
            repUser1.setStatus(Integer.parseInt(status));
        } catch (TransactionalException e) {
            userService.rollback();
            responseMessage = e.getMessage();
        }
        request.setAttribute("message",responseMessage);
        request.setAttribute("userifor",repUser1);
        request.getRequestDispatcher("/WEB-INF/view/admin/customers_edit.jsp").forward(request, response);
    }

    private String getStr (String case1, String case2) {
        return case1 == null || case1.trim().isEmpty() ? case2 : case1;
    }
}