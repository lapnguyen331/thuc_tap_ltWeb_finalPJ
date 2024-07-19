package com.project.models_rework.log;

import com.project.services.LogService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//định dạng log
public class LogFomatter {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    public static String format(LocalDateTime localDateTime, LogLevel level, String message) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = localDateTime.format(formatter);
//        return String.format("%s [%s] %s", formattedDateTime, level, message);
//    }
    public static String format(Log log) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = log.getLogTime().format(formatter);
        return String.format("%s [%s] %s", formattedDateTime, log.getLevel(), log.getMessage());
    }
    public static String createAtFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return String.format( formattedDateTime);
    }

    public static void main(String[] args) {
        LogService l = new LogService();
        Logger.info("lỗi ở dòng 15");
        System.out.println(format(l.getAll().get(0)));

    }
}
