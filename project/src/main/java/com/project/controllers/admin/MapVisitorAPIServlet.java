package com.project.controllers.admin;


import jakarta.servlet.ServletContext;
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
import org.json.JSONTokener;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MapVisitorAPIServlet", urlPatterns = {"/api/map"})
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
    protected Map<String, Integer> fetchJsonData() {
        ServletContext context = getServletContext();
        try  {
            InputStream is = context.getResourceAsStream("/WEB-INF/classes/map-data.json");
            Reader reader = new InputStreamReader(is, "UTF-8");
            JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
            Map<String, Integer> dataMap = new HashMap<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray innerArray = jsonArray.getJSONArray(i);
                String countryCode = innerArray.getString(0);
                int dataValue = innerArray.getInt(1);
                dataMap.put(countryCode, dataValue);
            }

            return dataMap;

        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);

        }
    }
    protected void loadDataMap(HttpServletResponse response){
        Map<String, Integer> cdata = fetchJsonData();
        if(cdata == null || cdata.isEmpty()){
            return;
        }
        else {
            //tao json
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                var out = response.getWriter();
                JSONArray master = new JSONArray();
                for (var entry : cdata.entrySet()) {
                    JSONArray array = new JSONArray();
                    array.put(entry.getKey());
                    array.put(entry.getValue());
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
    private void saveDataToJsonFile(Map<String, Integer> data) {
        String filePath = getServletContext().getRealPath("/WEB-INF/classes/map-data.json");

        try (FileWriter writer = new FileWriter(filePath)) {
            JSONArray jsonArray = new JSONArray();
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                JSONArray innerArray = new JSONArray();
                innerArray.put(entry.getKey());
                innerArray.put(entry.getValue());
                jsonArray.put(innerArray);
            }
            writer.write(jsonArray.toString());
        } catch (IOException  e ) {
            e.printStackTrace();
        }
    }


}