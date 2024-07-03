package com.project.models;

import javax.annotation.ManagedBean;
import java.io.Serializable;
import java.time.LocalDateTime;

@ManagedBean
public class Category implements Serializable {
    private int id;
    private String name;
    private Image thumbnail;
    private boolean status;
    private Blog blog;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Category(int id, String name, Image thumbnail, boolean status, Blog blog, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.status = status;
        this.blog = blog;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Category() {
    }

    public Category(int id) {
        this.id = id;
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

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thumbnail=" + thumbnail +
                ", status=" + status +
                ", blog=" + blog +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
