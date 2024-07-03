package com.project.models;

import java.time.LocalDateTime;

public class Blog {
    private int id;
    private User author;
    private String title;
    private boolean status;
    private String description;
    private String path;
    private Image thumbnail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Blog() {
    }

    public Blog(int id) {
        this.id = id;
    }

    public Blog(int id, User author, String title, boolean status, String description, String path, Image thumbnail, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.status = status;
        this.description = description;
        this.path = path;
        this.thumbnail = thumbnail;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
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
        return "Blog{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", thumbnail=" + thumbnail +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
