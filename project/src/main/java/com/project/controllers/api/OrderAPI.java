package com.project.controllers.api;

import com.project.models.Order;
import com.project.models.OrderItem;
import com.project.models.Product;
import com.project.services.CategoryService;
import com.project.services.OrderItemService;
import com.project.services.OrderService;
import com.project.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderAPIServlet", value = "/api/order/*")
public class OrderAPI extends HttpServlet {
    private OrderService orderService;
    private OrderItemService orderItemService;
    private ProductService productService;
    private CategoryService categoryService;

    public OrderAPI() {
        this.orderService = new OrderService();
        this.orderItemService = new OrderItemService(orderService.getHandle());
        this.productService = new ProductService(orderService.getHandle());
        this.categoryService = new CategoryService(orderService.getHandle());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/getAll":
                doGetAll(request, response);
                break;
            case "/getOrderItems":
                doGetOrderItems(request, response);
                break;
            case "/getOrder":
                doGetOrder(request, response);
                break;
        }
    }

    private void doGetOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.getOrderById(orderId);
        response.setContentType("application/json");
        var out = response.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("data", new JSONArray());
        obj.getJSONArray("data").put(getOrderJSON(order));
        out.print(obj);
        out.flush();
    }

    private void doGetOrderItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.getOrderById(orderId);
        List<OrderItem> items = orderItemService.getOrderItemOf(order);
        response.setContentType("application/json");
        var out = response.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("data", new JSONArray());
        for (var item : items) {
            obj.getJSONArray("data").put(getOrderItemJSON(item));
        }
        out.print(obj);
        out.flush();
    }

    private JSONObject getOrderItemJSON(OrderItem orderItem) {
        JSONObject obj = new JSONObject();
        obj.put("quantity", orderItem.getQuantity());
        obj.put("product", new JSONObject());
        {
            var product = productService.getById(orderItem.getProduct().getId());
            obj.getJSONObject("product").put("id", product.getId());
            obj.getJSONObject("product").put("name", product.getName());
            obj.getJSONObject("product").put("specific", product.getSpecification());
            obj.getJSONObject("product").put("image", product.getThumbnail().getPath());
            obj.getJSONObject("product").put("price", product.getPrice());

        }
        obj.put("price", orderItem.getPrice());
        return obj;
    }

    private void doGetAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Order> orders = orderService.getAll();
        response.setContentType("application/json");
        var out = response.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("data", new JSONArray());
        for (var order : orders) {
            obj.getJSONArray("data").put(getOrderJSON(order));
        }
        out.print(obj);
        out.flush();
    }

    private JSONObject getOrderJSON(Order order) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", order.getId());
        jsonObject.put("customer", order.getUser().getFirstName() + " " + order.getUser().getLastName());
        jsonObject.put("name", order.getReceiverName());
        jsonObject.put("price", order.getTotalPrice());
        jsonObject.put("phone", order.getReceiverPhone());
        jsonObject.put("email", order.getReceiverEmail());
        jsonObject.put("status", order.getStatus());
        jsonObject.put("create", order.getDayCreateAt());
        return jsonObject;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}