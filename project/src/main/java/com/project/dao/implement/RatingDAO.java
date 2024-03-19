package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.IRatingDAO;
import com.project.dao.IUserDAO;
import com.project.mappers.ImageRowMapper;
import com.project.mappers.ProductRowMapper;
import com.project.mappers.RatingRowMapper;
import com.project.mappers.UserRowMapper;
import com.project.models.Product;
import com.project.models.Rating;
import com.project.models.User;
import org.jdbi.v3.core.Handle;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class RatingDAO extends AbstractDAO<Rating> implements IRatingDAO {
    public RatingDAO(Handle handle) {
        super(handle);
    }

    @Override
    public List<Rating> getAll() {
        final String SELECT = "SELECT <columns> FROM <table1> r" +
                " JOIN <table2> u ON r.userId = u.id" +
                " JOIN <table3> p ON r.productId = p.id";
        return query(SELECT, Rating.class, (query -> {
            query.define("columns", "r.*, u.id, u.name, p.id, p.name");
        }), new UserRowMapper("u"), new ProductRowMapper("p"), new RatingRowMapper("r"));
    }

    @Override
    public List<Rating> getAllOf(Product product) {
        final String SELECT = "SELECT <columns> FROM <table1> r" +
                " JOIN <table2> u ON r.userId = u.id" +
                " WHERE productId = :product.id";
        return query(SELECT, Rating.class, (query -> {
            query.define("columns", "r.star, r.content, r.updateAt, u.firstName, u.lastName")
                    .define("table1", "ratings")
                    .define("table2", "users")
                    .bindBean("product", product);
        }), new UserRowMapper("u"), new RatingRowMapper("r"));
    }

    @Override
    public int insert(Rating r) {
        var keys = Arrays.asList(
                "userId",
                "productId",
                "star",
                "content",
                "createAt",
                "updateAt"
        );
        var values = Arrays.asList(
                r.getUser() == null ? null : r.getUser().getId(),
                r.getProduct() == null ? null : r.getProduct().getId(),
                r.getStar(),
                r.getContent(),
                r.getCreateAt() == null ? LocalDateTime.now() : r.getCreateAt(),
                r.getUpdateAt() == null ? LocalDateTime.now() : r.getUpdateAt()
        );
        final String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>)";
        return insertAndReturnGeneratedKeys(INSERT, "id", (update ->  {
            update.define("table", "ratings")
                    .defineList("columns", keys)
                    .bindList("values", values);
        })).mapTo(int.class).findOne().orElse(-1);
    }
}
