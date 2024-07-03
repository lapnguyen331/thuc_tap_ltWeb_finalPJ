package com.project.mappers;

import com.project.models.Blog;
import com.project.models.Category;
import com.project.models.Image;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CategoryRowMapper extends ARowMapper<Category> {
    public CategoryRowMapper(String alias) {
        super(alias);
    }

    public CategoryRowMapper() {
    }

    @Override
    public Category map(ResultSet rs, StatementContext ctx) {
        Category category;
        try {
            int id = getValueAt(rs, this.alias+"id", ctx, Integer.class);
            String name = getValueAt(rs, this.alias+"name", ctx, String.class);
            Image thumbnail = mapToIfPresent(Image.class, rs, ctx);
            boolean status = getValueAt(rs, this.alias+"status", ctx, Boolean.class);
            Blog blog = mapToIfPresent(Blog.class, rs, ctx);
            LocalDateTime createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
            LocalDateTime updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
            category = new Category(id, name, thumbnail, status, blog, createAt, updateAt);
        } catch (SQLException e) {
            e.printStackTrace();
            category = null;
        }
        return category;
    }
}
