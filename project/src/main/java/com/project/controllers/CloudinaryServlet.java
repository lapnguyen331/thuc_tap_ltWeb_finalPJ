package com.project.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.project.models.Contact;
import com.project.models.User;
import com.project.services.ContactService;
import com.project.services.UploadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "CloudinaryServlet", urlPatterns = {"/cloudinary/*"})
public class CloudinaryServlet extends HttpServlet {
    private UploadService uploadService;
    @Override
    public void init() throws ServletException {
        this.uploadService = new UploadService();
        super.init();
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            request.getRequestDispatcher("/WEB-INF/view/test_upload.jsp").forward(request, response);
            return;
        }
        User user = (User) request.getSession(false).getAttribute("user");
        switch (action.toLowerCase()) {
            case "/get-signature": {
                response.setContentType("application/json");
                String folder = "tmp/"+user.getUsername();
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                long timestamp = ts.getTime() / 1000;

                Map<String, Object> params = ObjectUtils.asMap(
                        "timestamp", timestamp,
                        "folder", folder
                );

                String signature = uploadService.signature(params);
                JSONObject json = new JSONObject(params);
                json.put("signature", signature);
                response.getWriter().print(json);
                response.getWriter().flush();
                break;
            }
            case "/search": {
                try {
                    response.setContentType("application/json");
                    String query = request.getParameter("query");
                    String expression = String.format("folder:tmp/%s/* && resource_type:image %s",
                            user.getUsername(), query != null && !query.isEmpty() ? "&& "+query : "");
                    JSONObject json = uploadService.searchImages(expression);
                    response.getWriter().write(json.toString());
                    response.getWriter().flush();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case "/revert-upload": {
                try {
                    response.setContentType("application/json");
                    String id = request.getParameter("id");
                    String public_id = "tmp/"+user.getUsername()+"/"+id;
                    ApiResponse resp = uploadService.remove(Arrays.asList(public_id), ObjectUtils.emptyMap());
                    response.getWriter().write(new JSONObject(resp).toString());
                    response.getWriter().flush();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case "/restore-image": {
                try {
                    var id = request.getParameter("id");
                    String public_id = "tmp/"+user.getUsername()+"/"+id;
                    JSONObject json = new JSONObject(uploadService.getResource(public_id, ObjectUtils.emptyMap()));
                    String mimeType = "image/"+json.getString("format");
                    String fileName = id+"."+json.getString("format");

                    response.setContentType(mimeType);
                    response.addHeader("Content-Disposition", "inline");
                    response.addHeader("Content-Dispotition", "filename=\""+fileName+"\"");

                    OutputStream out = response.getOutputStream();
                    InputStream in = new URL(uploadService.getURL(public_id)).openStream();
                    byte[] buffer = new byte[4096];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                    in.close();
                    out.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        response.setContentType("text/plain");
        if (action == null) return;
        switch (action.toLowerCase()) {
            case "/upload": {
                String public_id = request.getParameter("public_id");
                String signature = request.getParameter("signature");
                String version = request.getParameter("version");
                String resource_type = request.getParameter("resource_type");
                Map<String, Object> params = new HashMap<>();
                {
                    params.put("public_id", public_id);
                    params.put("version", version);
                }
                String resMsg = null;
                String expected = uploadService.upload(params);
                boolean validate = signature.equals(expected);
                resMsg = validate ? "Upload ảnh thành công" : "Upload ảnh thất bại";
                if (!validate) {
                    try {
                        ApiResponse resp = uploadService.destroy(resource_type, public_id);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                response.getWriter().write(resMsg);
                response.getWriter().flush();
                break;
            }
        }
    }
}