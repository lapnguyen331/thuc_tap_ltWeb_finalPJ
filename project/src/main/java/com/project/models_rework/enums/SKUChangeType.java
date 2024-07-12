package com.project.models_rework.enums;

public enum SKUChangeType {
    THEM_HANG("Thêm hàng"),
    HET_HAN("Hết ha"),
    BAN_CHO_KHACH("Bán cho khách"),
    KHACH_TRA_HANG("Khách trả hàng"),
    THEM_SKU_MOI("Thêm SKU mới");
    private final String text;

    SKUChangeType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
