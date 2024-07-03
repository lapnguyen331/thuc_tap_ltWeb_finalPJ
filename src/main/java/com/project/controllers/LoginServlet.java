package com.project.controllers;
import com.project.exceptions.NotVerifiedException;
import com.project.models.Cart;
import com.project.models.User;
import com.project.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.json.JSONObject;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        this.userService = new UserService();
        super.service(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardURL = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
//        System.out.println("forwardURL in doGet = "+forwardURL);
        HttpSession session = request.getSession(false);
//        CASE: Người dùng này đã đăng nhập trước đó, forward sang trang khác
        if (session.getAttribute("role") != null) {
            if (forwardURL == null) {
                response.sendRedirect("home");
                return;
            }
            response.sendRedirect(forwardURL);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var out = response.getWriter();
        response.setContentType("application/json");
        JSONObject json = new JSONObject();
        String msg = "";
        HttpSession session = request.getSession(false);
        String action = request.getParameter("loginAction");
        switch (action) {
            case "login": {
//        Lấy parameter
                String username = request.getParameter("username");
                System.out.println("username = "+username);
                String password = request.getParameter("password");
                System.out.println("password = "+password);

//         Kiểm tra đăng nhập...
                User user = userService.getUserByName(username);
                if (user == null) {
                    json.put("status", HttpServletResponse.SC_BAD_REQUEST);
                    msg = (username == null || username.trim().isEmpty()) ? "Tên đăng nhập không để trống!"
                            : (password == null || password.trim().isEmpty()) ? "Mật khẩu không để trống!"
                            : "Không tìm thấy tài khoản trong hệ thống!";
                }
                else {
                    String hashPassword = null;
                    try {
                        hashPassword = User.hashPassword(password);
                        if (!user.isVerified()) throw new NotVerifiedException();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        json.put("status", HttpServletResponse.SC_UNAUTHORIZED);
                        msg = "Xảy ra lỗi, thử lại sau...";
                        break;
                    } catch (NotVerifiedException e) {
                        json.put("status", HttpServletResponse.SC_UNAUTHORIZED);
                        msg = "Tài khoản này chưa được xác minh, vui lòng kiểm tra lại email!";
                        break;
                    }
                    if (user.getPassword().equals(hashPassword)) {
                        session.setAttribute("user", user);
                        session.setAttribute("role", user.getLevelAccess()+"");
                        json.put("status", HttpServletResponse.SC_OK);
                        msg = "Đăng nhập thành công!";
                    }
                    else {
                        json.put("status", HttpServletResponse.SC_UNAUTHORIZED);
                        msg = "Mật khẩu đăng nhập không đúng!";
                    }
                }
            }
            break;
            case "logout": {
                session.removeAttribute("user");
                session.removeAttribute("role");
                json.put("status", HttpServletResponse.SC_OK);
                msg = "Đăng xuất thành công";
            }
            break;
            default: {
//                Do nothing
            }
            break;
        }
        json.put("msg", msg);
        out.print(json);
        out.flush();
    }
}