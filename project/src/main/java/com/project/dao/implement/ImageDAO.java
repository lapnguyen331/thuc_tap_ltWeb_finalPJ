package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.IImageDAO;
import com.project.mappers.ImageRowMapper;
import com.project.models.Blog;
import com.project.models.Image;
import com.project.models.Product;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.time.LocalDateTime;
import java.util.*;

public class ImageDAO extends AbstractDAO<Image> implements IImageDAO {
    public ImageDAO(Handle handle) {
        super(handle);
    }

    @Override
    public List<Image> selectAll() {
        final String SELECT_ALL_IMG = "SELECT <columns> FROM <table> img";
        return query(SELECT_ALL_IMG, Image.class, (query) -> {
            query.define("table", "images").defineList("columns", "img.*");
        }, new ImageRowMapper("img"));
    }
    @Override
    public Image selectById(int id) {
        final String SELECT_IMG_BY_ID = "SELECT * FROM <table> img where img.id = :id";
        var result = query(SELECT_IMG_BY_ID, Image.class, (query) -> {
            query.define("table", "images")
                    .bind("id", id);
        }, new ImageRowMapper("img"));
        if (result.isEmpty()) return null;
        return result.get(0);
    }

    @Override
    public Image selectByPath(String path) {
        final String SELECT_IMG_BY_PATH = "SELECT * FROM <table> img WHERE img.path = :path";
        var rs = query(SELECT_IMG_BY_PATH, Image.class, (query) -> {
            query.define("table", "images")
                    .bind("path", path);
        }, new ImageRowMapper("img"));
        if (rs.isEmpty()) return null;
        return rs.get(0);
    }

    public String getPathById(int id) {
        final String SELECT_PATH_BY_ID = "SELECT img.path FROM <table> img where img.id = :id";
        var result = query(SELECT_PATH_BY_ID, String.class, (query) -> {
            query.define("table", "images")
                    .bind("id", id);
        });
        if (result.isEmpty()) return null;
        return result.get(0);
    }

    @Override
    public List<Image> selectGalleryOf(Product product) {
        final String SELECT_GALLERY_BY_PRODUCT =
                "SELECT <columns> FROM <table1> img " +
                "JOIN <table2> g ON img.id = g.imageId " +
                "WHERE g.productId = :getId";
        return query(SELECT_GALLERY_BY_PRODUCT, Image.class, ((query) -> {
            query.define("table1", "images")
                    .define("table2", "product_galleries")
                    .defineList("columns", "img.id", "img.path")
                    .bindMethods(product);
        }), new ImageRowMapper("img"));
    }

    @Override
    public int insert(Image image) {
        var values = Arrays.asList(image.getPath(), image.getUuid(),
                image.getCreateAt() == null ? LocalDateTime.now() : image.getCreateAt(),
                image.getUpdateAt() == null ? LocalDateTime.now() : image.getUpdateAt());
        final String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>);";
        return insertAndReturnGeneratedKeys(INSERT, "id", (update -> {
            update.defineList("columns", "path", "uuid", "createAt", "updateAt")
                    .define("table", "images")
                    .bindList("values", values);
        })).mapTo(int.class).one();
    }

    @Override
    public int insertToGalleryOf(Product p, Image i) {
        var values = Arrays.asList(p.getId(), i.getId());
        final String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>)";
        return insert(INSERT, update -> {
            update.define("table", "product_galleries")
                    .defineList("columns", "productId", "imageId")
                    .bindList("values", values);
        });
    }

    @Override
    public int insertToGalleryOf(Blog b, Image image) {
        var values = Arrays.asList(b.getId(), image.getId());
        final String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>)";
        return insert(INSERT, update -> {
            update.define("table", "blog_galleries")
                    .defineList("columns", "blogId, imageId")
                    .bindList("values", values);
        });
    }

    @Override
    public Image selectByUUID(String uuid) {
        final String SELECT_IMG_BY_ID = "SELECT * FROM <table> img where img.uuid = :uuid";
        var result = query(SELECT_IMG_BY_ID, Image.class, (query) -> {
            query.define("table", "images")
                    .bind("uuid", uuid);
        }, new ImageRowMapper("img"));
        if (result.isEmpty()) return null;
        return result.get(0);
    }

    @Override
    public int removeGalleryOf(Product p) {
        final String INSERT = "DELETE FROM <table> WHERE productId = :p.id";
        return insert(INSERT, update -> {
            update.define("table", "product_galleries")
                    .bindBean("p", p);
        });
    }

    public static void main(String[] args) {

    }
}
