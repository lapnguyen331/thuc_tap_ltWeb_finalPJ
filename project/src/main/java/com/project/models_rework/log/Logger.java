package com.project.models_rework.log;

import com.project.services.LogService;

import java.time.LocalDateTime;
import java.util.logging.Level;

public class Logger {
    static  LogService logService = new LogService();
    private static  void logDump(Log log) {
        logService.publish(log);
    }
    public static void info(String message) {
        logDump(Log.builder().level(String.valueOf(LogLevel.INFO)).message(message).logTime(LocalDateTime.now()).build());
    }

    public static  void warning(String message) {
        logDump(Log.builder().level(String.valueOf(LogLevel.WARNING)).message(message).logTime(LocalDateTime.now()).build());
    }
    public static  void error(String message) {
        logDump(Log.builder().level(String.valueOf(LogLevel.ERROR)).message(message).logTime(LocalDateTime.now()).build());
    }

    public static void main(String[] args) {
        Logger.error("lỗi trang home");
        Logger.error("lỗi trang home");

        Logger.error("lỗi trang home");
        Logger.error("lỗi trang home");
        Logger.error("lỗi trang home");
        Logger.error("lỗi trang home");
        Logger.info("lỗi trang home");
        Logger.warning("cảnh báo lỗi");





    }

}
