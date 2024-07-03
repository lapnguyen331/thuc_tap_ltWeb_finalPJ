package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.ICategoryDAO;
import com.project.mappers.BlogRowMapper;
import com.project.mappers.CategoryRowMapper;
import com.project.mappers.ImageRowMapper;
import com.project.mappers.ProductRowMapper;
import com.project.models.Category;
import com.project.models.Product;
import org.jdbi.v3.core.Handle;

import java.util.List;

public class CategoryDAO extends AbstractDAO<Category> implements ICategoryDAO {
    public CategoryDAO(Handle handle) {
        super(handle);
    }

    @Override
    public List<Category> getAll_fullDetails() {
        final String SELECT_ALL_CATEGORIES = "SELECT * FROM <table1> c" +
                " LEFT JOIN <table2> t ON c.thumbnail = t.id" +
                " LEFT JOIN <table3> b ON c.blogId = b.id";
        return query(SELECT_ALL_CATEGORIES, Category.class, (query) -> {
            query.define("table1", "categories")
                    .define("table2", "images")
                    .define("table3", "blogs");
        }, new CategoryRowMapper("c"), new ImageRowMapper("t"), new BlogRowMapper("b"));
    }

    @Override
    public List<Category> getAll_shortDetails() {
        final String SELECT_ALL = "SELECT <columns> FROM <table>";
        return query(SELECT_ALL, Category.class, (query) -> {
            query.define("table", "categories")
                    .defineList("columns", "id, name");
        }, new CategoryRowMapper());
    }



    @Override
    public Category findOneById(int id) {
        final String SELECT_ONE = "SELECT * FROM <table1> c" +
                " LEFT JOIN <table2> t ON <col1> = <col2>" +
                " LEFT JOIN <table3> b ON <col3> = <col4>" +
                " WHERE <col5> = <para>";
        var result = query(SELECT_ONE, Category.class, (query) -> {
            query.define("table1", "categories")
                    .define("table2", "images")
                    .define("table3", "blogs")
                    .define("col1", "c.thumbnail")
                    .define("col2", "t.id")
                    .define("col3", "c.blogId")
                    .define("col4", "b.id")
                    .define("col5", "c.id")
                    .define("para", id);
        }, new CategoryRowMapper("c"), new ImageRowMapper("t"), new BlogRowMapper("b"));
        if (result.isEmpty()) return null;
        return result.get(0);
    }

    public static void main(String[] args) {

    }
}
