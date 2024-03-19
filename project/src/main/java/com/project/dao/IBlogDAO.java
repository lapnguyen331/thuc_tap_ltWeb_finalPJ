package com.project.dao;
import com.project.models.Blog;

import java.sql.SQLException;
import java.util.List;

public interface IBlogDAO extends GenericDAO<Blog>{
    List<Blog> selectAll();
    Blog getBlogById(int id);
    int insert(Blog blog) throws Exception;
}
