package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.IBlogDAO;
import com.project.mappers.BlogRowMapper;
import com.project.mappers.ImageRowMapper;
import com.project.models.Blog;
import com.project.models.Image;
import org.jdbi.v3.core.Handle;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class BlogDAO extends AbstractDAO<Blog> implements IBlogDAO {
    public BlogDAO(Handle handle) {
        super(handle);
    }


    @Override
    public List<Blog> selectAll() {
        final String SELECT_ALL = "SELECT <columns> FROM <table1> b left JOIN <table2> i ON b.thumbnail = i.id";
        var result = query(SELECT_ALL, Blog.class, q -> {
            q.define("table1", "blogs")
                    .define("table2", "images")
                    .defineList("columns", "b.*, i.*");
        }, new BlogRowMapper("b"), new ImageRowMapper("i"));
        return result;
    }

    @Override
    public Blog getBlogById(int id) {
        final String SELECT = "SELECT <columns> FROM <table1> b LEFT JOIN <table2> i ON b.thumbnail = i.id WHERE b.id = :id";
        var result = query(SELECT, Blog.class, q -> {
            q.define("table1", "blogs")
                    .define("table2", "images")
                    .defineList("columns", "b.id, b.title, i.path, b.path")
                    .bind("id", id);
        }, new BlogRowMapper("b"), new ImageRowMapper("i"));
        if (result.isEmpty()) return null;
        return result.get(0);
    }

    @Override
    public int insert(Blog blog) throws Exception {
        var values = Arrays.asList(
                blog.getAuthor() == null ? null : blog.getAuthor().getId(),
                blog.getTitle(),
                blog.isStatus(),
                blog.getDescription(),
                blog.getPath(),
                blog.getThumbnail() == null ? null : blog.getThumbnail().getId(),
                blog.getCreateAt() == null ? LocalDateTime.now() : blog.getCreateAt(),
                blog.getUpdateAt() == null ? LocalDateTime.now() : blog.getUpdateAt()
        );
        final String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>)";
        return insertAndReturnGeneratedKeys(INSERT, "id", (update -> {
            update.define("table", "blogs")
                .defineList("columns", "userId", "title", "status", "description", "path", "thumbnail", "createAt", "updateAt")
                .bindList("values", values);
        })).mapTo(int.class).one();
    }


    public static void main(String[] args) {
    }
}
