package com.project.models_rework.log;


import com.project.service_rework.LogService;

import java.time.LocalDateTime;

public class Logger {
    static LogService logService = new LogService();
    private static  void logDump(Log log) {
        logService.publish(log);
    }
    public static void info(String message) {
        logDump(Log.builder().level(String.valueOf(LogLevel.INFO)).message(message).logTime(LocalDateTime.now()).ipAddress(null).build());
    }

    public static  void warning(String message) {
        logDump(Log.builder().level(String.valueOf(LogLevel.WARNING)).message(message).logTime(LocalDateTime.now()).ipAddress(null).build());
    }
    public static  void error(String message) {
        logDump(Log.builder().level(String.valueOf(LogLevel.ERROR)).message(message).logTime(LocalDateTime.now()).ipAddress(null).build());
    }
    public static void info(String ip,String message) {
        logDump(Log.builder().level(String.valueOf(LogLevel.INFO)).message(message).logTime(LocalDateTime.now()).ipAddress(ip).build());
    }

    public static  void warning(String ip,String message) {
        logDump(Log.builder().level(String.valueOf(LogLevel.WARNING)).message(message).logTime(LocalDateTime.now()).ipAddress(ip).build());
    }
    public static  void error(String ip,String message) {
        logDump(Log.builder().level(String.valueOf(LogLevel.ERROR)).message(message).logTime(LocalDateTime.now()).ipAddress(ip).build());
    }

    public static void main(String[] args) {

//        Logger.error("12.0.0.1","lỗi trang home");
//        Logger.error("lỗi trang home");
//
//        Logger.error("lỗi trang home");
//        Logger.error("lỗi trang home");
//        Logger.error("lỗi trang home");
//        Logger.error("lỗi trang home");
//        Logger.info("lỗi trang home");
//        Logger.warning("cảnh báo lỗi");




    }

}
