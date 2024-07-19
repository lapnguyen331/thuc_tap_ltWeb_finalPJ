package com.project.filters;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.project.models_rework.log.Logger;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;

import javax.json.Json;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@WebFilter(filterName = "global1", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class AuthenticationFilter implements Filter {
    String a[]
            = new String[] { "/track-ip", "/home", "/admin/dashboard","/","/search"};
    private List<String> trackIPPages = Arrays.asList(a);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = httpRequest.getRequestURI();
        ServletContext context = request.getServletContext();
        System.out.println("uri:"+uri+"*****");
        HttpSession session = httpRequest.getSession(false);
//        Get resource name
        int contextIndex = uri.indexOf(context.getContextPath());
        String resource = uri.substring(contextIndex + context.getContextPath().length());
        System.out.println("context:"+context+"****");
        System.out.println("resource:" + resource+"*****");
        System.out.println("index:"+contextIndex+"*****");
        for(String item :trackIPPages){
            if(item.trim().equals(uri.trim())){
                System.out.println("chuẩn bị track");
                processGeoLocation();
            }
        }
//        if (trackIPPages.contains(uri)) {
////            String clientIp = getClientIp(httpRequest);
//            trackIP();
//        }
//        Lỗi xảy ra ở Google Chrome, chuyển hướng về trang khởi đầu..
//        if (resource.isEmpty()) {
//            ((HttpServletResponse) r  esponse).sendRedirect("home");
//            return;
//        }
//        Get Authentication Properties
        Properties authProperties = (Properties) context.getAttribute("AUTHENTICATION_LIST");
//        Check resource authentication
        String rule = authProperties.getProperty(resource);
//        System.out.printf("permission of %s = %s\n", httpRequest.getRequestURI(), rule);
        // Track IP if the page is in the trackIPPages list

        if (rule != null) {
            if ((session.getAttribute("user") == null)) {
                httpRequest.setAttribute("message", "Xin lỗi bạn cần đăng nhập để tiếp tục...");
                httpRequest.getRequestDispatcher("/login").forward(httpRequest, httpResponse);
                return;
            } else if (!(rule.equals(session.getAttribute("role")))) {
                httpRequest.getRequestDispatcher("/home").forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private JsonObject trackIP() {
        String secretKey = "e560dcae828b4c5289260dab0abcfe6b"; // Thay thế bằng API key của bạn
        String url = "https://api.geoapify.com/v1/ipinfo?&apiKey=" + secretKey ;
        JsonParser jsonParser = new JsonParser(); // Gson JsonParser
        try {
            // Sử dụng Apache HttpClient để gửi yêu cầu GET
            Content content = Request.get(url)
                    .execute()
                    .returnContent();
            // In hoặc lưu thông tin IP
            JsonElement jsonResponse = jsonParser.parse(content.asString());
            System.out.println("thông tin Ip:"+jsonResponse.toString());
            // Kiểm tra nếu dữ liệu JSON là một đối tượng
            if (jsonResponse.isJsonObject()) {
                JsonObject jsonObject = jsonResponse.getAsJsonObject();
                System.out.println("Thông tin IP: " + jsonObject.toString());

                // Trả về đối tượng JSON
                return jsonObject;
            } else {
                System.err.println("Dữ liệu trả về không phải là JSON Object");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("ip tracking service not working");
        }
        return null;
    }
    public void processGeoLocation() {
        JsonObject jsonObject = trackIP();

        if (jsonObject != null) {
            String city = jsonObject.getAsJsonObject("city").get("name").getAsString();
            String country = jsonObject.getAsJsonObject("country").get("name").getAsString();
            double latitude = jsonObject.getAsJsonObject("location").get("latitude").getAsDouble();
            double longitude = jsonObject.getAsJsonObject("location").get("longitude").getAsDouble();
            JsonObject countryObject = jsonObject.getAsJsonObject("country");
            String isoCode = countryObject.get("iso_code").getAsString();
            String ip = jsonObject.has("ip") ? jsonObject.get("ip").getAsString() : "Không có thông tin";

            System.out.println("Thông tin IP: " + ip);
            System.out.println("ISO Code: " + isoCode);
            System.out.println("City: " + city);
            System.out.println("Country: " + country);
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);
            Logger.info(ip,
                    "Country: " +country+"\n" +
                    "City: "+city+"\n" +
                    "Latitude: 10.822"+latitude+"\n" +
                    "Longitude: 106.6257"+longitude+"\n" +
                    "Message: User accessed  page");
        }
    }
}