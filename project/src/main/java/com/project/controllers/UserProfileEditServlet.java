package com.project.controllers;

import com.project.models.Image;
import com.project.models.User;
import com.project.services.ImageService;
import com.project.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet(name = "UserProfileEditServlet", urlPatterns = {"/user-profile-edit"})
public class UserProfileEditServlet extends HttpServlet {
    private UserService userService;
    private ImageService imageService;

    @Override
    public void init() throws ServletException {
        this.userService= new UserService();
        this.imageService = new ImageService();
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
        request.getRequestDispatcher("/WEB-INF/view/user/user-profile-edit.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("off");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        User repuser = userService.getInforById(user.getId());
        String username = request.getParameter("usernamelogchange") ;
           String name = request.getParameter( "usernamechange");
           String email = request.getParameter("useremail");
           String phone = request.getParameter("userphone");
           String genderFlag= request.getParameter("flexRadioDefault");
           String address = request.getParameter("usernameaddress");
        String birth = request.getParameter("birthday");

        String firstname="";
           String lastname="";
           boolean nameflag = false;
           if(!(name ==null)){
               int index = name.lastIndexOf(" ");
               nameflag= true;
               if(index== -1){
                   lastname=name;
                   firstname=null;
               }
               else {
                   firstname = name.substring(0, index);
                   lastname = name.substring(index, name.length());
               }
           }
           String gender = "";
           switch (genderFlag){
               case "q1":
                   gender= "false";
                   break;
               case "q2":
                   gender= "true";
                   break;
               case "q3":
                   gender ="false";
                   break;
           }
           List<String> em = new ArrayList<>();
        username = ( username == null )? repuser.getUsername():username;
        email = ( email == null )? repuser.getEmail():email;
        phone = ( phone == null )? repuser.getPhone():phone;
        address = ( address == null )? repuser.getAddress():address;

//        gender = (gender.isEmpty() || gender == null )? String.valueOf(repuser.isGender()):gender;
        firstname = ( firstname == null )? repuser.getFirstName():firstname;
        lastname = ( lastname == null )? repuser.getLastName():lastname;
        gender = gender.equals("true")?"1":"0";
        String responseMessage="cập nhật thành công";
        userService.begin();
        userService.updateInfor(repuser.getId(),username,firstname,lastname,email,phone,address,gender,birth);
        userService.commit();
        repuser.setUsername(username);
        user.setUsername(repuser.getUsername());

        System.out.println(gender);
        request.setAttribute("message",responseMessage);
        request.setAttribute("userifor",repuser);
        request.getRequestDispatcher("/WEB-INF/view/user/user-profile-edit.jsp").include(request, response);

    }
}