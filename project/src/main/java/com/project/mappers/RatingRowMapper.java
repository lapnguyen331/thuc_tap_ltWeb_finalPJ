package com.project.mappers;

import com.project.models.Image;
import com.project.models.Product;
import com.project.models.Rating;
import com.project.models.User;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RatingRowMapper extends ARowMapper<Rating> {
    public RatingRowMapper(String alias) {
        super(alias);
    }

    public RatingRowMapper() {
    }

    @Override
    public Rating map(ResultSet rs, StatementContext ctx) throws SQLException {
        int id = getValueAt(rs, this.alias+"id", ctx, int.class);
        User user = mapToIfPresent(User.class, rs, ctx);
        Product product = mapToIfPresent(Product.class, rs, ctx);
        int star = getValueAt(rs, this.alias+"star", ctx, int.class);
        String content = getValueAt(rs, this.alias+"content", ctx, String.class);
        LocalDateTime createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
        LocalDateTime updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
        return new Rating(id, user, product, star, content, createAt, updateAt);
    }
}
