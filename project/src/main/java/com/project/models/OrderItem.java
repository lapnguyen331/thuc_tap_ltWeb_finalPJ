package com.project.models;

import java.time.LocalDateTime;

public class OrderItem {
    private Order order;
    private Product product;
    private int quantity;
    private float price;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public OrderItem(Order order, Product product, int quantity, float price, LocalDateTime createAt, LocalDateTime updateAt) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public OrderItem() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
    public float countTotal(float price,int quantity ){
        return price*quantity;
    }
    public String getStringPrice(float price) {
        String r = (int) price+"";
        r = r.replaceAll("(?<=\\d)(?=(\\d{3})+(?!\\d))", ".");
        return r;
    }


    @Override
    public String toString() {
        return "OrderItem{" +
                "order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
