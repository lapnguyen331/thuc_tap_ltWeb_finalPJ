package com.project.dao;

import com.project.models.Category;
import com.project.models.Product;

import java.io.LineNumberInputStream;
import java.util.List;

public interface ICategoryDAO {
    Category findOneById(int id);
    List<Category> getAll_fullDetails();
    List<Category> getAll_shortDetails();
//    List<Product> getAllProductOfCate(Category c);

}
