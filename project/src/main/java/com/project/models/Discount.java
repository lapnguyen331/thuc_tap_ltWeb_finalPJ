package com.project.models;

import java.time.LocalDateTime;

public class Discount {
    private int id;
    private String name;
    private float discountPercent;
    private boolean status;
    private LocalDateTime startAt;
    private LocalDateTime finishAt;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Discount() {
    }

    public Discount(int id) {
        this.id = id;
    }

    public Discount(int id, String name, float discountPercent, boolean status, LocalDateTime startAt, LocalDateTime finishAt, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.discountPercent = discountPercent;
        this.status = status;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(LocalDateTime finishAt) {
        this.finishAt = finishAt;
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

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discountPercent=" + discountPercent +
                ", status=" + status +
                ", startAt=" + startAt +
                ", finishAt=" + finishAt +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
