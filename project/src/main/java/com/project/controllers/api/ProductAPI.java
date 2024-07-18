package com.project.controllers.api;

import com.project.models.Order;
import com.project.models.Product;
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

@WebServlet(name = "ProductAPIServlet", value = "/api/product/*")
public class ProductAPI extends HttpServlet {
    private ProductService productService;

    public ProductAPI() {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/getAll":
                doGetAll(request, response);
                break;
        }
    }

    private void doGetAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> products = productService.getAll_FullDetails();
        JSONObject obj = new JSONObject();
        response.setContentType("application/json");
        var out = response.getWriter();
        obj.put("data", new JSONArray());
        for (var product : products) {
            obj.getJSONArray("data").put(getProductJSON(product));
        }
        out.print(obj);
        out.flush();
    }

    private JSONObject getProductJSON(Product product) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", product.getId());
        jsonObject.put("name", product.getName());
        jsonObject.put("specific", product.getSpecification());
        jsonObject.put("price", product.getPrice());
        jsonObject.put("discount", product.getDiscount().getDiscountPercent());
        jsonObject.put("image", product.getThumbnail().getPath());
        jsonObject.put("category", product.getCategory().getId());
        jsonObject.put("quantity", product.getQuantity());
        return jsonObject;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}