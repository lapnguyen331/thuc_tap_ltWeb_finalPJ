package com.project.dto.vnpay;

import java.io.Serializable;

public class PaymentResDTO implements Serializable {
    private String status;
    private String meassage;
    private String URL;

    public PaymentResDTO() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMeassage() {
        return meassage;
    }

    public void setMeassage(String meassage) {
        this.meassage = meassage;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "PaymentResDTO{" +
                "status='" + status + '\'' +
                ", meassage='" + meassage + '\'' +
                ", URL='" + URL + '\'' +
                '}';
    }
}
