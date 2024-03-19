package com.project.models;

import java.time.LocalDateTime;

public class Image {
    private int id;
    private String path;
    private String uuid;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Image() {
    }

    public Image(int id, String path, String uuid, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.path = path;
        this.uuid = uuid;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Image(int id) {
        this.id = id;
    }

    public Image(int id, String path, String uuid) {
        this.id = id;
        this.path = path;
        this.uuid = uuid;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
        return "Image{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", uuid='" + uuid + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
