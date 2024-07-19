package com.project.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.Url;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.project.models_rework.log.Logger;
import com.project.service_rework.ProductScriptService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.*;

public class UploadService {
    private static Cloudinary cloudinary = null;
    private static Properties prop = new Properties();
    private static String PUBLIC_KEY;
    private static String SECRET_KEY;
    private static String CLOUD_NAME;
    static {
        try {
            prop.load(UploadService.class.getClassLoader().getResourceAsStream("cloudinary.properties"));
            PUBLIC_KEY = prop.getProperty("cloudinary.public_key");
            SECRET_KEY = prop.getProperty("cloudinary.secret_key");
            CLOUD_NAME = prop.getProperty("cloudinary.cloud_name");
            cloudinary = new Cloudinary(String.format("cloudinary://%s:%s@%s", PUBLIC_KEY, SECRET_KEY, CLOUD_NAME));
            cloudinary.config.secure = true;
        } catch (IOException ioException) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ioException.printStackTrace(pw);
            Logger.error(sw.toString());
        }
    }
    public String upload(Map<String, Object> params) {
        return cloudinary.apiSignRequest(params, SECRET_KEY);
    }

    public String signature(Map<String, Object> params) {
        String signature = cloudinary.apiSignRequest(params, SECRET_KEY);
        return signature;
    }

    public JSONObject searchImages(String expression) throws Exception {
        var array = new JSONObject(
                cloudinary.search()
                .expression(expression).execute()
        ).getJSONArray("resources");
        List<String> names = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) names.add(array.getJSONObject(i).getString("filename"));
        JSONObject rs = new JSONObject();
        rs.put("keys", names);
        return rs;
    }

    public ApiResponse destroy(String resource_type, String public_id) throws Exception {
        return cloudinary.api().deleteResources(Arrays.asList(public_id), ObjectUtils.asMap(
                "resource_type", resource_type));
    }

    public String getURL(String public_id) throws Exception {
        Transformation transformation = new Transformation().width(500).height(500).crop("fill");
        return cloudinary.url().transformation(transformation).generate(public_id);
    }

    public ApiResponse remove(List<String> public_id, Map<String, Object> params) throws Exception {
        return cloudinary.api().deleteResources(public_id, params);
    }

    public List<String> uploadImagesByURL(List<String> urls, String folder) throws Exception {
        List<String> rs = new ArrayList<>();
        try {
            for (String url : urls) {
                var response = cloudinary.uploader().upload(url, ObjectUtils.asMap(
                        "folder", folder
                ));
                System.out.println(response.get("public_id"));
                rs.add((String) response.get("public_id"));
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace();
            Logger.error(sw.toString());
            cloudinary.api().deleteResourcesByPrefix(folder+"/", ObjectUtils.emptyMap());
            System.out.println("Xảy ra lỗi, đã xóa toàn bộ folder: "+folder);
        }
        return rs;
    }

    public ApiResponse deleteFolder(String folderName) throws Exception {
        // Xoá folder
        return cloudinary.api().deleteFolder(folderName, ObjectUtils.emptyMap());
    }

    public ApiResponse deleteAllResources(String folderName, Map<String, Object> options) throws Exception {
        // Xoá toàn bộ resource bên trong folder
        return cloudinary.api().deleteResourcesByPrefix(folderName+"/", options);
    }

    public ApiResponse getResource(String public_id, Map<String, Object> params) throws Exception {
        return cloudinary.api().resource(public_id, params);
    }

    public Map uploadFile(File file, Map<String, Object> params) throws IOException {
        return cloudinary.uploader().upload(file, params);
    }

    public static void main(String[] args) throws Exception {
        var list = Arrays.asList("https://kgin.com.vn/wp-content/uploads/2023/10/set-1-kgin-min.jpg",
                "https://kgin.com.vn/wp-content/uploads/2023/10/bo-qua-tang-hong-sam-kgin.jpg",
                "https://kgin.com.vn/wp-content/uploads/2023/10/set1-tri-an-950k-kgin.jpg",
                "https://kgin.com.vn/wp-content/uploads/2023/10/set-1-tri-an-scaled.jpg",
                "https://kgin.com.vn/wp-content/uploads/2023/10/bo-qua-tang-kin-scaled.jpg");
        var instance = new UploadService();
//        var rs = instance.uploadImagesByURL(list, "assest/products/product__1");
        var service = new ProductScriptService();
        File file = service.convertBlogToFile("<h1>HEADER</h1>");
        System.out.println(instance.uploadFile(file, Map.of(
                "resource_type", "raw",
                "folder", "assest/products/product__1"
        )));
    }
}
