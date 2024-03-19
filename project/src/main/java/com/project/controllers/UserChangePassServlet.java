package com.project.controllers;

import com.project.exceptions.NotEnoughQuantityException;
import com.project.exceptions.NotFoundProductException;
import com.project.models.Image;
import com.project.models.User;
import com.project.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "UserChangePassServlet", urlPatterns = {"/user-profile-changePass"})
public class UserChangePassServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService= new UserService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        User repUser1 = userService.getInforById(user.getId());

        request.setAttribute("userifor",user);
        request.getRequestDispatcher("/WEB-INF/view/user/user-profile-changePass.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        User repUser1 = userService.getInforById(user.getId());
        if(repUser1.getAvatar().getPath().isEmpty()){
            repUser1.setAvatar(new Image(000,"/inventory/images/user-profile/use-avatar-header-default.jpg",null,null,null));
        }else{
            Image em = repUser1.getAvatar();
            repUser1.setAvatar(new Image(em.getId(),"/files/"+ em.getPath(),null,null,null));
        }
        String cusername = request.getParameter("cusername");
        String cpassword = request.getParameter("cpassword");
        String email =  repUser1.getEmail();

        repUser1.setUsername(cusername);
//        repUser1.setPassword(cpassword);
        String responseMessage = "cập nhật email thành công!";
        try {
            userService.begin();
            userService.changePass(repUser1.getId(),cusername,User.hashPassword(cpassword));
            userService.commit();
            repUser1.setUsername(cusername);
            user.setUsername(repUser1.getUsername());
            user.setAvatar(repUser1.getAvatar());

        } catch (NoSuchAlgorithmException e) {
            userService.rollback();
            responseMessage = e.getMessage();
        }

        request.setAttribute("message",responseMessage);
        request.setAttribute("userifor",user);

        request.getRequestDispatcher("/WEB-INF/view/user/user-profile-changePass.jsp").include(request, response);

    }
}