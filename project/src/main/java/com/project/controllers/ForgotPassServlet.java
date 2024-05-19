package com.project.controllers;

import com.project.models.User;
import com.project.services.MailService;
import com.project.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

@WebServlet(name = "ForgotPassServlet", urlPatterns = {"/forgotpassword"})
public class ForgotPassServlet extends HttpServlet {

    private UserService userService;
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        this.userService = new UserService();
        super.service(req, res);
    }
    @Override
    public void init() throws ServletException {

        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        request.getRequestDispatcher("/WEB-INF/view/forgot_password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int code = 200;

        String email= request.getParameter("email");
        String responseMsg = null;
        userService.begin();
        List<User> userList = userService.getUserByMail(email.trim());
        if(userList.isEmpty()){
            responseMsg = "không tồn tại email này trong hệ thống";
        }
        else {
            try {
                String randompass = getRandomPass();
                System.out.println(userList.get(0).toString());
                int userId = userService.changePass(userList.get(0).getId(),userList.get(0).getUsername(),User.hashPassword(randompass));
                MailService.sendMail("Website ginseng - reset mật khẩu","HỆ THỐNG WEB BÁN NHÂN SÂM GINSENG\n" +
                        "\n" +
                        "Chúng tôi đã hỗ trỡ bạn  reset mật khẩu.\n Đây là mật khẩu mới của bạn:" +randompass+
                        "\n" +
                        "Trân trọng!\n" +
                        "Cảm ơn",email.trim());
                responseMsg = String.format("Đăng kí tài khoản thành công, kiểm tra email %s để lấy mã xác minh", email.trim());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        request.setAttribute("code", code);
        request.setAttribute("message", responseMsg);
        request.getRequestDispatcher("/WEB-INF/view/forgot_password.jsp").forward(request, response);

    }
    private String getRandomPass (){
        char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")).toCharArray();
        int randomStrLength =9;
        String randomStr = RandomStringUtils.random( randomStrLength, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
        System.out.println( "mật khẩu random " +randomStr );
        return randomStr;
    }

}