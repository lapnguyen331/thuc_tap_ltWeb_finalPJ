package com.project.services;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaService {
    public static final String HOST_NAME = "smtp.gmail.com";
    public static final int SSL_PORT = 465; // Port for SSL
    public static final int TSL_PORT = 587; // Port for TLS/STARTTLS
    public static final String APP_EMAIL = "nqat0919@gmail.com"; // your email
    public static final String APP_PASSWORD = "bmmn uaoy qxrm qzru"; // your password
    public static final String RECEIVE_EMAIL = "nqat0919@gmail.com";
    public static boolean sendMail(String title, String content, String receiver) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.socketFactory.port", SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", SSL_PORT);
        // get Session
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });
        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECEIVE_EMAIL));
            message.setSubject("Testing Subject");
            message.setText("Hello theree");
            // send message
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
