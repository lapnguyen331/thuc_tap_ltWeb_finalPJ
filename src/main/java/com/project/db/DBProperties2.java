package com.project.db;

import java.io.IOException;
import java.util.Properties;

public class DBProperties2 {
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(DBProperties2.class.getClassLoader().getResourceAsStream("db2.properties"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String getDbHost() {
        return prop.get("db.host").toString();
    }

    public static String getDbPort() {
        return prop.get("db.port").toString();
    }


    public static String getUsername() {
        return prop.get("db.username").toString();
    }

    public static String getPassword() {
        return prop.get("db.password").toString();
    }

    public static String getDbOption() {
        return prop.get("db.options").toString();
    }

    public static String getDbName() {
        return prop.get("db.databaseName").toString();
    }
}
