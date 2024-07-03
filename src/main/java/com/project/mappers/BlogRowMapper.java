package com.project.mappers;

import com.project.models.Blog;
import com.project.models.Image;
import org.jdbi.v3.core.statement.StatementContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BlogRowMapper extends ARowMapper<Blog> {

    public BlogRowMapper(String alias) {
        super(alias);
    }

    @Override
    public Blog map(ResultSet rs, StatementContext ctx) throws SQLException {
        Blog blog;
        try {
            int id = getValueAt(rs, this.alias+"id", ctx, int.class);
            String title = getValueAt(rs, this.alias+"title", ctx, String.class);
            boolean status = getValueAt(rs, this.alias+"status", ctx, boolean.class);
            String description = getValueAt(rs, this.alias+"description", ctx, String.class);
            String path = getValueAt(rs, this.alias+"path", ctx, String.class);
            Image thumbnail = mapToIfPresent(Image.class, rs, ctx);
            LocalDateTime createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
            LocalDateTime updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
            blog = new Blog(id, null, title, status, description, path, thumbnail, createAt, updateAt);
        } catch (SQLException e) {
            e.printStackTrace();
            blog = null;
        }
        return blog;
    }
}
