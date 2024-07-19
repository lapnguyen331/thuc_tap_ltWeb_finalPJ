package com.project.controllers.admin;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MapVisitorAPIServlet", urlPatterns = {"/admin/api/map"})
public class MapVisitorAPIServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        loadDataMap(response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected Map<String, Integer> fetchJsonData(){
        String filePath = getServletContext().getRealPath("/data.json")
        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser(reader);

            //Read JSON file
            Object obj = jsonParser.parse();

            JSONArray dataList = (JSONArray) obj;
            System.out.println(dataList);
            Map<String,Integer> datamap = new HashMap<>();
            for (int i = 0; i < dataList.length(); i++) {
                JSONArray innerArray = (JSONArray) dataList.get(i);
                String countryCode = (String) innerArray.get(0); // Temporary variable for countryCode
                int dataValue = innerArray.getInt(1); // Temporary variable for dataValue
                datamap.put(countryCode,dataValue);
            }
            return datamap;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
    protected void loadDataMap(HttpServletResponse response){
        Map<String, Integer> cdata = new HashMap<>();
        cdata.put("li", 500);
        cdata.put("at", 200);
        cdata.put("sz", 300);
        cdata.put("hu", 150);

        //tao json
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            var out = response.getWriter();
            JSONArray master = new JSONArray();
            for (var entry : cdata.entrySet()) {
                JSONArray array = new JSONArray();
                array.put(entry.getKey());
                array.put( entry.getValue());
                master.put(array);
            }


            String data = master.toString();
            out.write(data);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}