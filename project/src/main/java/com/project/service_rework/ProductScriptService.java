package com.project.service_rework;

import com.google.gson.Gson;
import com.project.dao_rework.BlogDAO;
import com.project.dao_rework.ImageDAO;
import com.project.dao_rework.ProductDAO;
import com.project.helpers.ProductScriptJSON;
import com.project.helpers.SQLScript;
import com.project.models_rework.Blog;
import com.project.models_rework.Image;
import com.project.models_rework.Product;
import com.project.utils.MyUtil;
import io.leangen.geantyref.TypeToken;
import org.jdbi.v3.core.Handle;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductScriptService extends AbstractService {
    public File convertBlogToFile(String content) throws FileNotFoundException {
        String fileName = MyUtil.generateUUID()+".txt";
        File file = new File(fileName);
        PrintWriter writer = new PrintWriter(file);
        writer.println(content);
        writer.close();
        return file;
    }
    public void insertFromJSON(String jsonFile) {
        Handle myHandle = getHandle();
        begin();
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(
                            ProductScriptService.class.getClassLoader()
                            .getResource(jsonFile).getPath(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            reader.lines().forEach(line -> sb.append(line+"\n"));
            Gson gson = new Gson();
            Type listType = new TypeToken<List<ProductScriptJSON>>() {}.getType();
            List<ProductScriptJSON> listJSON = gson.fromJson(sb.toString(), listType);
            var productDAO = myHandle.attach(ProductDAO.class);
            var imageDAO = myHandle.attach(ImageDAO.class);
            var blogDAO = myHandle.attach(BlogDAO.class);
            var upload = new UploadService();
            for (var json: listJSON) {
                // Insert Product
                Product product = Product.builder()
                        .name(json.name).brand(json.name)
                        .categoryId(json.categoryId).quantity(json.quantity)
                        .producerId(json.producerId).status(json.status == 1)
                        .price(json.price).minAge(json.minAge)
                        .description(json.description).discountId(json.discountId)
                        .specification(json.specification).weight(json.weight)
                        .createAt(LocalDateTime.now()).updateAt(LocalDateTime.now())
                        .build();
                Integer productId = productDAO.insert(product);
                System.out.println("Insert product id = " +productId);


                // Insert ảnh thumbnail product
                String uuid = MyUtil.generateUUID();
                Map apiResponse;
                apiResponse = upload.uploadFileByURL(json.thumbnail, Map.of(
                        "public_id", uuid,
                        "folder", "inventory/products/product__"+productId
                ));
                System.out.println("Upload image cloudinary: "+ apiResponse.get("secure_url"));
                Image image = Image.builder()
                        .uuid(uuid).path(apiResponse.get("public_id").toString())
                        .updateAt(LocalDateTime.now()).createAt(LocalDateTime.now())
                        .build();
                Integer thumbnailId = imageDAO.insert(image);
                System.out.println("Insert thumbnail id = "+thumbnailId);
                productDAO.changeThumbnail(productId, thumbnailId);
                System.out.println("Update product id = "+productId);

//                Insert Blog
                Blog blog = Blog.builder()
                        .description(json.description).title(json.name)
                        .status(true).thumbnail(thumbnailId)
                        .userId(null).createAt(LocalDateTime.now())
                        .updateAt(LocalDateTime.now())
                        .build();
                Integer blogId = blogDAO.insert(blog);
                System.out.println("Insert blog id = "+blogId);
                File blogFile = convertBlogToFile(json.blog);
                apiResponse = upload.uploadFile(blogFile, Map.of(
                        "resource_type", "raw",
                        "folder", "inventory/blogs/blog__"+blogId
                ));
                System.out.println("Upload blog cloudinary: "+ apiResponse.get("secure_url"));
                blogDAO.updatePath(blogId, apiResponse.get("public_id").toString());
                System.out.println("Update blog id = "+blogId);
                productDAO.changeBlog(productId, blogId);
                System.out.println("Update product id = "+productId);

//                Insert Gallery của product
                for (var link : json.galleries) {
                    uuid = MyUtil.generateUUID();
                    apiResponse = upload.uploadFileByURL(link, Map.of(
                            "public_id", uuid,
                            "folder", "inventory/products/product__"+productId
                    ));
                    System.out.println("Upload image cloudinary: "+ apiResponse.get("secure_url"));
                    image = Image.builder()
                            .uuid(uuid).path(apiResponse.get("public_id").toString())
                            .updateAt(LocalDateTime.now()).createAt(LocalDateTime.now())
                            .build();
                    var galleryId = imageDAO.insert(image);
                    productDAO.insertToGallery(productId, galleryId);
                    System.out.println("Insert gallery image with id: "+ galleryId);
                }
                System.out.println("==========Done Product with id: "+productId);
            }
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        }
    }

    public static void main(String[] args) {
        var instance = new ProductScriptService();
        instance.insertFromJSON("product_category_1.txt");
    }
}
