package com.project.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.accessBySocial.google.Constants;
import com.project.dto.UserGoogleDTO;

import com.project.models.User;
import com.project.models_rework.log.Logger;
import com.project.services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import jakarta.servlet.http.HttpSession;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONObject;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/login-google"})
public class LoginGoogleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginGoogleServlet() {
        super();
    }
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    protected UserGoogleDTO processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogleDTO user = getUserInfo(accessToken);

        System.out.println(user);
        return user;
    }

    public static String getToken(String code) throws IOException {
        // call api to get token
        String response = Request.post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("vào được google session");
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        JSONObject json = new JSONObject();
        String msg = "";
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
            Logger.warning(this.getClass().toString() +" : Failed login by google");
            dis.forward(request, response);
        }
        else {
            UserGoogleDTO usergog = processRequest(request, response);
            UserService userService = new UserService();
            User userDB = userService.getUserByMail(usergog.getEmail());
            if (userDB == null) {
                insertGoogleAccount(request,response,userService,usergog);

            }
            //lấy lại dữ liệu đã add
            User currentUser = userService.getUserByMail(usergog.getEmail());//chắc chắn có dữ liệu
            HttpSession session = request.getSession();
//                    CartDAO.updateCart(userDB.getId()); //update cart lên session --> undone
            session.setAttribute("user", currentUser);
            session.setAttribute("role", currentUser.getLevelAccess() + "");
            json.put("status", HttpServletResponse.SC_OK);
            msg = "Đăng nhập thành công!";
            //chuyển hướng theo level access
            int role = currentUser.getLevelAccess();
            session.setAttribute("role", role + "");
            System.out.println(role);
            if (role == 1) {//admin
                // redirect to admin page
//                        MyUtils.setUserRole(session, "Admin");
//                response.sendRedirect("admin/profile");
                Logger.info("Admin login by google success");
                response.sendRedirect("home");
            } else if (role == 0) { //khách hàng
                // redirect to home
//                        MyUtils.setUserRole(session, "User");
                System.out.println("vào dc kh");
                Logger.info("User login by google success");
                response.sendRedirect("home");
            }
//                    else if (result.equals("Manager")) { //phát triển thêm các vai trò khác
//                        // redirect to home
//                        MyUtils.setUserRole(session, "MANAGER");
//                        response.sendRedirect(request.getContextPath() + "/admin/profile");
//                      }
        }

    }

    //trường hợp tài khoản đăng nhập bằng google chưa có trong database --> insert tk vào database
    protected void insertGoogleAccount(HttpServletRequest request, HttpServletResponse response,UserService userService,UserGoogleDTO usergog)
            throws ServletException, IOException {
        System.out.println("insert vào data base");
        String username = usergog.getName();
        if (username == null) {
            username = usergog.exactUserNameGoogle(usergog.getEmail());
        }
        try {
            int count = userService.addNewGoogleUser(username, usergog.getEmail(), 0, usergog.getFamily_name(), usergog.getPicture()); //add tài khoản google vào db
        } catch ( NoSuchAlgorithmException e) {
            System.out.println("lỗi ko tạo được pass googleSignup");
            Logger.error(this.getClass().toString() +": Password creation failed");
        }
    }
    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }


}