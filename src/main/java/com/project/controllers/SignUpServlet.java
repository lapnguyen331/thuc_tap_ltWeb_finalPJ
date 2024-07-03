package com.project.controllers;

import com.project.exceptions.DuplicateInfoUserException;
import com.project.models.Image;
import com.project.models.User;
import com.project.services.MailService;
import com.project.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet(name = "SignUpServlet", urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        this.userService = new UserService();
        super.service(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int code = 200;
        String responseMsg = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int levelAccess = 0;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String phone = request.getParameter("phone");
        Date birth = null;
        int status = 0;
        String email = request.getParameter("email");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birth = format.parse(request.getParameter("dob"));
        } catch (ParseException e) {
            responseMsg = "Xin lỗi hệ thống không thể định dạng ngày tháng năm vừa nhập";
            e.printStackTrace();
        }
        boolean verified = false;
        User user = null;
        try {
            user = new User(-1, username, User.hashPassword(password), null, levelAccess, firstName, lastName, gender, null, phone,
                    birth, status, email, verified, null, null, User.getUUID(), LocalDateTime.now());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        userService.begin();
        try {
            int userId = userService.insert(user);
            userService.commit();
            user.setId(userId);
            MailService.sendMail("Xác minh tài khoản", getBaseURL(request)+"/activation?id="+user.getId()+"&token="+user.getToken(), email);
            responseMsg = String.format("Đăng kí tài khoản thành công, kiểm tra email %s để lấy mã xác minh", email);
        } catch (DuplicateInfoUserException e) {
            code = 300;
            responseMsg = e.getMessage();
            userService.rollback();
            e.printStackTrace();
        } catch (MessagingException e) {
            code = 500;
            responseMsg = "Xảy ra lỗi trong quá trình gửi mã xác nhận, thử lại sau.";
            userService.rollback();
            e.printStackTrace();
        }
        request.setAttribute("code", code);
        request.setAttribute("message", responseMsg);
        request.getRequestDispatcher("/WEB-INF/view/signup.jsp").forward(request, response);
    }
    private String getBaseURL(HttpServletRequest request) {
        String scheme = request.getScheme();
        String host = request.getHeader("Host");        // includes server name and server port
        String contextPath = request.getContextPath();  // includes leading forward slash

        String resultPath = scheme + "://" + host + contextPath;
        return resultPath;
    }
}