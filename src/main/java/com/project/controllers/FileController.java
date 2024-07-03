package com.project.controllers;

import com.project.helpers.PropertiesFileHelper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.Properties;

@WebServlet(name = "FileController", urlPatterns = {"/files/*"})
public class FileController extends HttpServlet {
    private static final Properties prop = PropertiesFileHelper.getProperties("external.properties");
    private static final int DEFAULT_BUFFER_SIZE = 102400;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestedFile = request.getPathInfo();
//        System.out.println("requestedFile = "+requestedFile);
        File file = new File(request.getServletContext().getRealPath("/assests/"+requestedFile));
        if (!file.exists()) {
            String file_folder = prop.getProperty("external.path");
            String path = file_folder+requestedFile;
//            System.out.println("path = "+path);
            file = new File(path);
            if (!file.exists()) return;
            // Get content type by filename.
            String contentType = getServletContext().getMimeType(file.getName());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
//            System.out.println("contentType = "+contentType);
            // Init servlet response.
            response.reset();
            response.setBufferSize(DEFAULT_BUFFER_SIZE);
            response.setContentType(contentType);
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");


            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int read = -1;
            while ((read = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, read);
            }
            bis.close();
            bos.close();
            return;
        }
        request.getRequestDispatcher("/assests/"+requestedFile).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}