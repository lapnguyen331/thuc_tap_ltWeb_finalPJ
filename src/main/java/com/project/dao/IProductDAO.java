package com.project.dao;

import com.project.models.Category;
import com.project.models.Product;

import java.util.List;

public interface IProductDAO {
    boolean checkExists(int id);
    Product selectOne_fullDetails(int id);
    Product selectOne_shortDetails(int id);
    List<Product> selectAll_fullDetails();
    List<Product> selectAll_shortDetails();
    List<Product> selectTop4_shortDetails();
    List<Product> selectTop3ProductsOf_shortDetails(Category category);
    List<Product> selectTopProductsOf_shortDetails(Category category, int n);
    List<String> selectAllBrands();
    List<Product> searchProduct(String name, int categoryId, String brand, String minPrice, String maxPrice);
    List<Product> searchProduct(String name, List<Integer> categoryIds, List<String> brands);
    int insert(Product p);
    int update(Product p);
    List<Product> selectALLProductsOfCate_shortDetails(Category category) ;
}
