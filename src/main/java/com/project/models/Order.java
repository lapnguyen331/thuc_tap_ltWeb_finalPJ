package com.project.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class Order {
    private int id;
    private User user;
    private float totalPrice;
    private int status;
    private String receiverName, receiverAddress, receiverPhone, receiverEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Order(int id, User user, float totalPrice, int status, String receiverName, String receiverAddress, String receiverPhone, String receiverEmail, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverPhone = receiverPhone;
        this.receiverEmail = receiverEmail;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getCreateAt1() {
        return createAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    public LocalDateTime getCreateAt(){
        return this.createAt;
    }


    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
    public String  getDateOnly(LocalDateTime in){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYYY");
        return in.format(formatter);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
