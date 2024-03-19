package com.project.mails;

import java.io.IOException;
import java.util.Properties;

public class MailProperties {
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(MailProperties.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String getMailHost() {
        return prop.get("mail.smtp.host").toString();
    }

    public static String getSSLPort() {
        return prop.get("mail.smtp.port.ssl").toString();
    }
    public static String getTSL() {
        return prop.get("mail.smtp.port.tsl").toString();
    }


    public static String getAppEmail() {
        return prop.get("mail.smtp.app.email").toString();
    }

    public static String getAppPassword() {
        return prop.get("db.password").toString();
    }

    public static String getDbOption() {
        return prop.get("db.options").toString();
    }

    public static String getDbName() {
        return prop.get("db.databaseName").toString();
    }
}
