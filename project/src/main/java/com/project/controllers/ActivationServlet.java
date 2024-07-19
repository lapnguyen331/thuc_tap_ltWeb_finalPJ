package com.project.controllers;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.project.exceptions.AlreadyVerifiedException;
import com.project.exceptions.DuplicateInfoUserException;
import com.project.exceptions.NotFoundUserException;
import com.project.models.User;
import com.project.models_rework.log.Logger;
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

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@WebServlet(name = "ActivationServlet", urlPatterns = {"/activation"})
public class ActivationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        this.userService = new UserService();
        super.service(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseMsg = null;
        int code = 200;
        String token = request.getParameter("token");
        String action = request.getParameter("action");
        if (action != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (action.equals("resend")) {
                var email = userService.getInforById(id).getEmail();
                String newToken = UUID.randomUUID().toString();
                userService.updateToken(id, newToken);
                try {
                    MailService.sendMail("Xác minh tài khoản", getBaseURL(request)+"/activation?id="+id+"&token="+newToken, email);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                code = 300;
                responseMsg = "Đã gửi lại mã xác nhận";
                Logger.info("Sending verify mail success: userId = "+id);
                request.setAttribute("code", code);
                request.setAttribute("message", responseMsg);
                request.setAttribute("userid", id);
                request.getRequestDispatcher("/WEB-INF/view/signup.jsp").forward(request, response);
                return;
            }
        }
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            if (!userService.validateToken(id, token)) {
                code = 300;
                responseMsg = "Mã xác minh không chính xác!";
                Logger.warning("Incorrect verification code : userID" + id);
                request.setAttribute("resend", true);
            }
            else
                Logger.info("Account verification successful: userId " +id);
                responseMsg = "Xác minh tài khoản thành công!";
        } catch (TimeoutException e) {
            var email = userService.getInforById(id).getEmail();
            try {
                String newToken = UUID.randomUUID().toString();
                userService.updateToken(id, newToken);
                MailService.sendMail("Xác minh tài khoản", getBaseURL(request)+"/activation?id="+id+"&token="+newToken, email);
                code = 300;
                Logger.info("Sending verify mail success: mail ="+email);
                responseMsg = "Mã xác minh của bạn đã hết hạn, đã gửi lại mã mới trong tài khoản email";
            } catch (MessagingException ex) {
                code = 500;
                Logger.error(this.getClass().toString() +" : Failed to send verification code to email : email = "+email);
                responseMsg = "Hệ thống không thể gửi mã xác minh đến email, thử lại sau...";
            }
        } catch (NotFoundUserException e) {
            code = 300;
            Logger.warning("User not found for the provided token");
            responseMsg = "Không tìm thấy user có id tương ứng với token này!";
        } catch (AlreadyVerifiedException e) {
            code = 300;
            Logger.info("Account already verified");
            responseMsg = "Tài khoản này đã được xác minh!";
        }
        request.setAttribute("userid", id);
        request.setAttribute("code", code);
        request.setAttribute("message", responseMsg);
        request.getRequestDispatcher("/WEB-INF/view/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private String getBaseURL(HttpServletRequest request) {
        String scheme = request.getScheme();
        String host = request.getHeader("Host");        // includes server name and server port
        String contextPath = request.getContextPath();  // includes leading forward slash

        String resultPath = scheme + "://" + host + contextPath;
        return resultPath;
    }
}