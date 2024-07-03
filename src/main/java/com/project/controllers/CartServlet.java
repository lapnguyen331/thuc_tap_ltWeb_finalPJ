package com.project.controllers;
import com.project.models.Cart;
import com.project.models.CartItem;
import com.project.models.Product;
import com.project.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartAction", urlPatterns = {"/cartAction", "/cart"})
public class CartServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession(false).getAttribute("cart");
        String action = request.getParameter("action");
        if (action != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            var product  = productService.getById(id);
            switch (action) {
                case "add":
                    cart.putIn(product, quantity);
                    break;
                case "remove":
                    cart.putOut(product, quantity);
                    break;
            }
            response.sendRedirect(getServletContext().getContextPath()+"/cart");
            return;
        }
        List<CartItem> list = new ArrayList<>();
        for (var entry : cart.getProducts().entrySet()) {
            Product product = productService.getById_short(entry.getKey());
            int quantity = entry.getValue().getQuantity();
            list.add(new CartItem(product, quantity));
        }
        if (!list.isEmpty()) request.setAttribute("items", list);
        request.getRequestDispatcher("/WEB-INF/view/details_cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var out = response.getWriter();
        response.setContentType("application/json");
        JSONObject json = new JSONObject();
        String action = request.getParameter("action");
        Cart cart = (Cart) request.getSession(false).getAttribute("cart");
        if (action == null) {
            doGet(request, response);
            return;
        }
        switch (action) {
            case "get": {
                var set = cart.getProducts().entrySet();
                JSONArray arr = new JSONArray();
                JSONObject obj;
                for (var entry : set) {
                    obj = new JSONObject();
                    obj.put("id", entry.getKey());
                    obj.put("item", JSONObject.wrap(entry.getValue()));
                    arr.put(obj);
                }
                out.print(arr);
                out.flush();
            }
            break;
            case "add": {
                int id = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                var product  = productService.getById(id);
                cart.putIn(product, quantity);
                json.put("status", HttpServletResponse.SC_OK);
                json.put("msg", "Đã cập nhật thay đổi giỏ hàng!");
                out.print(json);
                out.flush();
            }
            break;
            case "remove": {
                int id = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                var product  = productService.getById(id);
                cart.putOut(product, quantity);
                json.put("status", HttpServletResponse.SC_OK);
                json.put("msg", "Đã cập nhật thay đổi giỏ hàng!");
                out.print(json);
                out.flush();
            }
            break;
        }
    }
}