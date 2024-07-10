package com.project.utils;

import java.util.UUID;

public class MyUtil {
    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }
}
