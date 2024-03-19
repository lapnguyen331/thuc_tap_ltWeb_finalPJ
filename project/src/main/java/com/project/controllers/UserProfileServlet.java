package com.project.controllers;

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

@WebServlet(name = "UserProfileServlet", urlPatterns = {"/user-profile"})
public class UserProfileServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService= new UserService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
//        doPost(request,response);
        User user = (User) session.getAttribute("user");
        User repUser = userService.getInforById(user.getId());
        if(repUser.getAvatar().getPath().isEmpty()){
            repUser.setAvatar(new Image(000,"/inventory/images/user-profile/use-avatar-header-default.jpg",null,null,null));
        }else{
            Image em = repUser.getAvatar();
            repUser.setAvatar(new Image(em.getId(),"/files/"+ em.getPath(),null,null,null));
        }
        request.setAttribute("userifor",repUser);
        request.getRequestDispatcher("/WEB-INF/view/user/user-profile.jsp").forward(request, response);

//        request.getRequestDispatcher("/WEB-INF/view/user/user-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}