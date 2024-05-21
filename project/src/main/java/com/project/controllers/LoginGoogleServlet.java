package com.project.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.accessGoogle.Constants;
import com.project.dao.implement.CartDAO;
import com.project.dao.implement.UserDAO;
import com.project.dto.UserGoogleDTO;

import com.project.exceptions.DuplicateInfoUserException;
import com.project.models.Image;
import com.project.models.User;
import com.project.services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    protected UserGoogleDTO processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogleDTO user = getUserInfo(accessToken);

        System.out.println(user);
        return user;
    }

    public static String getToken(String code) throws ClientProtocolException,  IOException {
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

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the +
    // sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        JSONObject json = new JSONObject();
        String msg = "";
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
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
                response.sendRedirect("home");
            } else if (role == 0) { //khách hàng
                // redirect to home
//                        MyUtils.setUserRole(session, "User");
                System.out.println("vào dc kh");
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
        String username = usergog.getName();
        if (username == null) {
            username = usergog.exactUserNameGoogle(usergog.getEmail());
        }
        try {
            int count = userService.addNewGoogleUser(username, usergog.getEmail(), 0, usergog.getFamily_name(), usergog.getPicture()); //add tài khoản google vào db
        } catch ( NoSuchAlgorithmException e) {
            System.out.println("lỗi ko tạo được pass googleSignup");
        }
    }
    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}