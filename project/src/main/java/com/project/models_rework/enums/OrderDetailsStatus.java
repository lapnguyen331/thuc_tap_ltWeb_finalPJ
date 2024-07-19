package com.project.models_rework.enums;

public enum OrderDetailsStatus {
    DA_XU_LY("Đã xử lý"),
    CHUA_XU_LY("Chưa xử lý");
    private final String text;

    OrderDetailsStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
