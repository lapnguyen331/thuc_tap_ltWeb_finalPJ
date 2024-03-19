package com.project.models;

import org.jdbi.v3.core.mapper.Nested;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Product implements Serializable {
    private int id;
    private String name;
    private float price;
    private int quantity;
    private int minAge;
    private Image thumbnail;
    private String specification;
    private float weight;
    private boolean status;
    private String brand;
    private String description;
    private Producer producer;
    private Category category;
    private Discount discount;
    private Blog blog;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Product(int id, String name, float price, int quantity, int minAge, Image thumbnail, String specification, float weight, boolean status, String brand, String description, Producer producer, Category category, Discount discount, Blog blog, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.minAge = minAge;
        this.thumbnail = thumbnail;
        this.specification = specification;
        this.weight = weight;
        this.status = status;
        this.brand = brand;
        this.description = description;
        this.producer = producer;
        this.category = category;
        this.discount = discount;
        this.blog = blog;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Product() {

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
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

    public String getStringPrice(float price) {
        String r = (int) price+"";
        r = r.replaceAll("(?<=\\d)(?=(\\d{3})+(?!\\d))", ".");
        return r;
    }

    public String getStringDayCreateAt() {
        return String.format("%s-%s-%s", this.getCreateAt().getDayOfMonth(),
                this.getCreateAt().getMonthValue() + 1,
                this.getCreateAt().getYear());
    }

    public float getDiscountPrice() {
        return this.price / 100 * (100 - this.discount.getDiscountPercent());
    }

    public String getRoundPrice(float price){
        return String.valueOf((int)price);
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", minAge=" + minAge +
                ", thumbnail=" + thumbnail +
                ", specification='" + specification + '\'' +
                ", weight=" + weight +
                ", status=" + status +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", producer=" + producer +
                ", category=" + category +
                ", discount=" + discount +
                ", blog=" + blog +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new Product().getStringPrice(9188000f));
    }
}
