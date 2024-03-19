package com.project.helpers;
import com.project.dao.IBlogDAO;
import com.project.dao.IImageDAO;
import com.project.dao.IProductDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.dao.implement.ImageDAO;
import com.project.db.JDBIConnector;
import com.project.db.JDBIConnector2;
import com.project.models.*;
import com.project.services.BlogService;
import com.project.services.ImageService;
import org.jdbi.v3.core.Handle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;

public class SQLScript {
    int month;
    int day;
    String DEST_IMAGES;
    String DEST_BLOGS;
    String DEST;
    IBlogDAO blogDAO;
    IImageDAO imageDAO;
    IProductDAO productDAO;
    Handle handle;
    static Properties prop = PropertiesFileHelper.getProperties("external.properties");
    private SQLScript() {
        this.month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        this.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        this.DEST_IMAGES = prop.getProperty("external.path.images");
        this.DEST_BLOGS = prop.getProperty("external.path.blogs");
        this.DEST = prop.getProperty("external.path");
        this.handle = JDBIConnector.get().open();

        this.blogDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_BLOG);
        this.imageDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_IMAGE);
        this.productDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_PRODUCT);
    }

    private void downloadImage(String url, String fileName) throws IOException, URISyntaxException {
        URL connect = new URL(url);
//        String fileName = Paths.get(new URI(url).getPath()).getFileName().toString();
        String dest = String.format(DEST_IMAGES+"\\%s\\%s", month, day);
        Files.createDirectories(Paths.get(dest));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest+"/"+fileName));
        BufferedInputStream bis = new BufferedInputStream(connect.openStream());
        byte[] buffer = new byte[102400];
        int read = -1;
        while ((read = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, read);
        }
        bis.close();
        bos.close();
        System.out.println("Đã tải xong file: "+fileName);
    }
    private void writeBlogContent(String content, String fileName) throws IOException {
        String dest = String.format(DEST_BLOGS+"\\%s\\%s", month, day);
        Files.createDirectories(Paths.get(dest));
        BufferedReader reader = new BufferedReader(new StringReader(content));
        PrintWriter writer = new PrintWriter(dest+"\\"+fileName);
        reader.lines().forEach(line -> writer.println(line));
        writer.close();
        reader.close();
        System.out.println("Viết xong file: "+fileName);
    }
    private Blog insertBlog(JSONObject json) throws Exception {
        String INSERT = "INSERT INTO blogs (title, description, path) VALUES (:title, :description, :path)";
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String fileName = uuid+".txt";
        writeBlogContent(json.getString("blog"), fileName);
        Blog blog = new Blog(
                -1,
                null,
                json.getString("name"),
                true, json.getString("description"),
                String.format("blogs/%s/%s/%s", month, day, fileName),
                null,
                null,
                null
        );
        int id = blogDAO.insert(blog);
        blog.setId(id);
        System.out.println("Insert blog "+uuid+" vào database");
        return blog;
    }
    private Product insertProduct(JSONObject json) throws Exception {
        Product product = new Product(
                -1,
                json.getString("name"),
                json.getFloat("price"),
                json.getInt("quantity"),
                json.getInt("minAge"),
                new Image(json.getInt("thumbnail")),
                json.getString("specification"),
                json.getFloat("weight"),
                json.getInt("status") > 0,
                json.getString("brand"),
                json.getString("description"),
                new Producer(json.getInt("producerId")),
                new Category(json.getInt("categoryId")),
                new Discount(json.getInt("discountId")),
                new Blog(json.getInt("blogId")),
                null,
                null);
        int id = productDAO.insert(product);
        product.setId(id);
        System.out.println("Insert sản phẩm "+json.getString("name")+" vào database!");
        return product;
    }
    public void insertFromJSON(String jsonFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(SQLScript.class.getClassLoader().getResource(jsonFile).getPath(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        reader.lines().forEach(line -> sb.append(line+"\n"));
        JSONArray array = new JSONArray(sb.toString());
        String message = null;
        try {
            handle.begin();
            ImageService imageService = new ImageService(handle);
            BlogService blogService = new BlogService(handle);
            for (int i = 0; i < array.length(); i++) {
                var json = array.getJSONObject(i);
//            Download và Insert ảnh vào database
                json.put("thumbnail", imageService.downloadAndInsert(json.getString("thumbnail"), DEST));
                String blogContent = json.getString("blog");
                String title = json.getString("name");
                String description = json.getString("description");
                int blogId = blogService.insertBlog("", DEST, null, title, true, description, blogContent, null, null, null);
                json.put("blogId", blogId);
                var product = insertProduct(json);
                var jsonArray = json.getJSONArray("galleries");
                for (int j = 0; j < jsonArray.length(); j++) {
                    String link = jsonArray.getString(j);
                    int imgId = imageService.downloadAndInsert(link, DEST);
                    imageDAO.insertToGalleryOf(product, new Image(imgId));
                }
            }
            handle.commit();
            message = String.format("Đã insert xong toàn bộ dữ liệu trong file %s", jsonFile);
        } catch (Exception e) {
            e.printStackTrace();
            message = String.format("File %s xảy ra lỗi, tiến hành rollback transaction...", jsonFile);
            handle.rollback();
        } finally {
            handle.close();
            System.out.println("=============================================================");
            System.out.println(message);
        }
    }

    public static SQLScript get() {
        return new SQLScript();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
//        SQLScript.get().insertFromJSON("product_category_1.txt");
//        SQLScript.get().insertFromJSON("product_category_2.txt");
//        SQLScript.get().insertFromJSON("product_category_3.txt");
//        SQLScript.get().insertFromJSON("product_category_4.txt");
//        SQLScript.get().insertFromJSON("product_category_5.txt");
//        SQLScript.get().insertFromJSON("product_category_7.txt");
//        SQLScript.get().insertFromJSON("product_category_8.txt");
//        SQLScript.get().insertFromJSON("product_category_9.txt");
    }
}
