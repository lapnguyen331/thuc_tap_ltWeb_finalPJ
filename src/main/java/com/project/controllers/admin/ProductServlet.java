package com.project.controllers.admin;

import com.project.helpers.PropertiesFileHelper;
import com.project.models.Product;
import com.project.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "AdminProduct", urlPatterns = {"/admin/product"})
public class ProductServlet extends HttpServlet {
    private static final Properties prop = PropertiesFileHelper.getProperties("external.properties");
    private final ProductService productService;
    private static final int DEFAULT_BUFFER_SIZE = 102400;

    public ProductServlet() {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var brands = productService.getBrands();
        request.setAttribute("brands", brands);
        request.getRequestDispatcher("/WEB-INF/view/admin/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        var out = response.getWriter();
        if (action != null) {
            response.setContentType("text/plain");
            String json = null;
            switch (action) {
                case "getAll":
                {
                    json = getJSONProducts(productService.getAll_FullDetails());
                    out.println(json);
                    out.flush();
                    break;
                }
                case "filter":
                {
                    List<String> brands = new ArrayList<>();
                    List<Integer> categoryIds = new ArrayList<>();
                    String name = request.getParameter("name");
                    if (name.isEmpty()) name = null;
                    for (String brand : request.getParameterValues("brand")) {
                        if (brand == null || brand.isEmpty()) continue;
                        brands.add(brand);
                    }
                    for (String id : request.getParameterValues("category")) {
                        if (id == null || id.isEmpty()) continue;
                        categoryIds.add(Integer.parseInt(id));
                    }
                    json = getJSONProducts(productService.search(name, categoryIds, brands));
                    break;
                }
            }
            out.println(json);
            out.flush();
        }
    }

    private String getJSONProducts(List<Product> products) {
        JSONArray data = new JSONArray();
        for (var product : products) {
            JSONObject obj = new JSONObject();
            obj.put("id", product.getId())
                    .put("name", product.getName())
                    .put("specific", product.getSpecification())
                    .put("discount", product.getDiscount().getDiscountPercent())
                    .put("image", product.getThumbnail().getPath())
                    .put("rate", 4.9)
                    .put("price", product.getStringPrice(product.getPrice()))
                    .put("category", product.getCategory().getId())
                    .put("quantity", product.getQuantity());
            data.put(obj);
        }
        JSONObject rs = new JSONObject();
        rs.put("data", data);
        return rs.toString();
    }
}