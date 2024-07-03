package com.project.helpers;

import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileHelper {
    public static Properties getProperties(ServletContext context, String fileRelativePath) {
        InputStream input = context.getResourceAsStream(fileRelativePath);
        Properties prop = null;
        try {
            prop = new Properties();
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static Properties getProperties(String propertiesFile) {
        InputStream input = PropertiesFileHelper.class.getClassLoader().getResourceAsStream(propertiesFile);
        Properties prop = null;
        try {
            prop = new Properties();
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
