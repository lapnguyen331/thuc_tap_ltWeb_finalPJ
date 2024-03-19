package com.project.dao;

import com.project.models.Blog;
import com.project.models.Image;
import com.project.models.Product;

import java.sql.SQLException;
import java.util.List;

public interface IImageDAO extends GenericDAO<Image>{
    List<Image> selectAll();
    Image selectById(int id);
    Image selectByPath(String path);
    List<Image> selectGalleryOf(Product p);
    int insert(Image image);
    int insertToGalleryOf(Product p, Image image);
    int insertToGalleryOf(Blog b, Image image);
    Image selectByUUID(String uuid);
    int removeGalleryOf(Product p);
}
