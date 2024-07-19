package com.project.models_rework.log;


import com.project.service_rework.LogService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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
        return String.format("%s [%s] [IP: %s] %s", formattedDateTime, log.getLevel(), log.getIpAddress(),log.getMessage());
    }
    public static String createAtFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return String.format( formattedDateTime);
    }

    public static void main(String[] args) {
        LogService l = new LogService();
        Logger.warning("12.0.0.1","lỗi ở dòng này");
        List<Log> em = l.getAll();
        for(Log i : em){
            System.out.println(format(i));
        }
    }
}
