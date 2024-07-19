package com.project.mails;

import com.project.models_rework.log.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

public class MailProperties {
    private static final Properties prop = new Properties();

    static {
        try {
            prop.load(MailProperties.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException ioException) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ioException.printStackTrace(pw);
            Logger.error(sw.toString());
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
