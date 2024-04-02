package com.project.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.Url;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
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
            ioException.printStackTrace();
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

    public ApiResponse destroy(String resource_type, String public_id) {
        try {
            return cloudinary.api().deleteResources(Arrays.asList(public_id), ObjectUtils.asMap(
                    "resource_type", resource_type
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            e.printStackTrace();
            cloudinary.api().deleteResourcesByPrefix(folder+"/", ObjectUtils.emptyMap());
            System.out.println("Xảy ra lỗi, đã xóa toàn bộ folder: "+folder);
        }
        return rs;
    }

    public ApiResponse getResource(String public_id, Map<String, Object> params) throws Exception {
        return cloudinary.api().resource(public_id, params);
    }

    public static void main(String[] args) throws Exception {
        var list = Arrays.asList("https://kgin.com.vn/wp-content/uploads/2023/10/set-1-kgin-min.jpg",
                "https://kgin.com.vn/wp-content/uploads/2023/10/bo-qua-tang-hong-sam-kgin.jpg",
                "https://kgin.com.vn/wp-content/uploads/2023/10/set1-tri-an-950k-kgin.jpg",
                "https://kgin.com.vn/wp-content/uploads/2023/10/set-1-tri-an-scaled.jpg",
                "https://kgin.com.vn/wp-content/uploads/2023/10/bo-qua-tang-kin-scaled.jpg");
        var instance = new UploadService();
        var rs = instance.uploadImagesByURL(list, "assest/products/product__1");
        System.out.println(rs);
    }
}
