package com.project.controllers.admin;

import com.mysql.cj.exceptions.OperationCancelledException;
import com.project.exceptions.NotFoundProductException;
import com.project.helpers.PropertiesFileHelper;
import com.project.models.Image;
import com.project.models.Product;
import com.project.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@MultipartConfig()
@WebServlet(name = "AdminProductDetail_edit", urlPatterns = {"/admin/product/detail"})
public class ProductDetailServlet extends HttpServlet {
    private final ProductService productService;
    private final ImageService imageService;
    private final ProducerService producerService;
    private final CategoryService categoryService;
    private final BlogService blogService;

    public ProductDetailServlet() {
        this.productService = new ProductService();
        this.imageService = new ImageService();
        this.producerService = new ProducerService();
        this.categoryService = new CategoryService();
        this.blogService = new BlogService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getById(id);
        var galleries = imageService.getGalleriesOf(product);
        var producers = producerService.getAll();
        request.setAttribute("product", product);
        request.setAttribute("galleries", galleries);
        request.setAttribute("producers", producers);
        request.getRequestDispatcher("/WEB-INF/view/admin/product_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doUpdate(request, response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        var category = categoryService.findCateById(Integer.parseInt(request.getParameter("categoryId")));
        String specification = request.getParameter("specification");
        float weight = Float.parseFloat(request.getParameter("weight"));
        String brand = request.getParameter("brand");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int minAge = Integer.parseInt(request.getParameter("minAge"));
        var producer = producerService.getById(Integer.parseInt(request.getParameter("producerId")));
        String description = request.getParameter("description");
        LocalDateTime updateAt = LocalDateTime.now();
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        var blog = blogService.findById(Integer.parseInt(request.getParameter("blogId")));
        Part thumbnail_new = request.getParts().stream()
                .filter(p -> "thumbnail-new-image[]".equals(p.getName()))
                .filter(p -> p.getSize() != 0)
                .findFirst().orElse(null);
        String[] thumbnail_old = request.getParameterValues("thumbnail-old-image[]");
        Image thumbnail = null;
        List<Part> gallery_new = request.getParts().stream().filter(p -> "gallery-new-image[]".equals(p.getName()))
                .filter(p -> p.getSize() != 0)
                .toList();
        String[] gallery_old = request.getParameterValues("gallery-old-image[]");
        try {
            imageService.begin();
            var handle = imageService.getHandle();
            var sub_productService = new ProductService(handle);
            if (thumbnail_new != null) {
//                System.out.println(request.getServletContext().getRealPath("/assests"));
                int imgId = imageService.createAndInsert(thumbnail_new, request.getServletContext().getRealPath("/assests"));
                thumbnail = imageService.findOneById(imgId);
            } else if (thumbnail_old != null) {
                String uuid = getUUID(thumbnail_old[0]);
                thumbnail = imageService.findOneByUUID(uuid);
            }
            Product product = sub_productService.getById(id);
            {
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setMinAge(minAge);
                product.setThumbnail(thumbnail);
                product.setSpecification(specification);
                product.setWeight(weight);
                product.setStatus(status);
                product.setBrand(brand);
                product.setDescription(description);
                product.setProducer(producer);
                product.setCategory(category);
                product.setBlog(blog);
                product.setUpdateAt(updateAt);
            }
            int update = sub_productService.updateProduct(product);
            if (update <= 0) throw new OperationCancelledException();
            imageService.clearGalleries(product);
            if (!gallery_new.isEmpty()) {
                for (Part p : gallery_new) imageService.createAndInsert(p, request.getServletContext().getRealPath("/assests"));
            }
            if (gallery_old != null) {
                for (String s : gallery_old) {
                    String uuid = getUUID(s);
                    var gallery = imageService.findOneByUUID(uuid);
                    imageService.insertToGalleryOf(product, gallery);
                }
            }
            imageService.rollback();
        } catch (IOException e) {
            e.printStackTrace();
            imageService.rollback();
        } catch (NotFoundProductException e) {
            throw new RuntimeException(e);
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
    private String getUUID(String link) {
        String fileName = link.substring(link.lastIndexOf('/')+1, link.length());
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}