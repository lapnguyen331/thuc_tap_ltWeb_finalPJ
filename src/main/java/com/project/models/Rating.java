package com.project.models;

import java.time.LocalDateTime;

public class Rating {
    private int id;
    private User user;
    private Product product;
    private int star;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Rating(int id, User user, Product product, int star, String content, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.star = star;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Rating() {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
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

    public String dateTimeToString(LocalDateTime date) {
        int year = date.getYear();
        int month = date.getMonthValue() + 1;
        int day = date.getDayOfMonth();
        int hour = date.getHour();
        int minute = date.getMinute();
        return String.format("%s/%s/%s - %s:%s", day, month, year, (hour<10?"0":"")+hour, (minute<10?"0":"")+minute);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", star=" + star +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
