package com.project.services;

import com.project.helpers.PropertiesFileHelper;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailService {
    static Properties prop = PropertiesFileHelper.getProperties("mail.properties");
    public synchronized static boolean sendMail (String title, String content, String receiver) throws MessagingException {
        final String APP_EMAIL = prop.getProperty("app.email");
        final String APP_PASSWORD = prop.getProperty("app.password");
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        message.setSubject(title);
        message.setText(content);
        // send message
        Transport.send(message);
        System.out.println("Message sent successfully");
        return true;
    }

    public static void main(String[] args) throws MessagingException {
        MailService.sendMail("Mail test", "Nội dung test", "21130601@st.hcmuaf.edu.vn");
    }
}
