package com.project.services;

import com.project.helpers.PropertiesFileHelper;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class IOService {
    static Properties prop = PropertiesFileHelper.getProperties("external.properties");

    private IOService() {}

    public static IOService getInstance() {
        return new IOService();
    }

    public String readTxtFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder rs = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            rs.append(line).append("\n");
        }
        return rs.toString();
    }

    public String readText(String url) throws IOException {
        URL connect = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connect.openStream()));
        StringBuilder rs = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            rs.append(line).append("\n");
        }
        return rs.toString();
    }

    public File downloadImageFromURL(String url, String dest, String fileName) throws IOException {
        URL connect = new URL(url);
//        String fileName = Paths.get(new URI(url).getPath()).getFileName().toString();
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
//        System.out.println("Đã tải xong file: "+fileName);
        return new File(dest+"/"+fileName);
    }

    public File writeBlog(String blogContent, String dest, String fileName) throws IOException {
        Files.createDirectories(Paths.get(dest));
        BufferedReader reader = new BufferedReader(new StringReader(blogContent));
        PrintWriter writer = new PrintWriter(dest+"\\"+fileName);
        reader.lines().forEach(line -> writer.println(line));
        writer.close();
        reader.close();
        System.out.println("Viết xong file: "+fileName);
        return new File(dest+"\\"+fileName);
    }
}
