package com.project.mappers;

import com.project.models.*;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class ProductRowMapper extends ARowMapper<Product> {
    public ProductRowMapper(String alias) {
        super(alias);
    }

    public ProductRowMapper() {
    }

    @Override
    public Product map(ResultSet rs, StatementContext ctx) {
        Product product;
        try {
            int id = getValueAt(rs, this.alias+"id", ctx, int.class);
            String name = getValueAt(rs, this.alias+"name", ctx, String.class);
            float price = getValueAt(rs, this.alias+"price", ctx, float.class);
            int quantity = getValueAt(rs, this.alias+"quantity", ctx, int.class);
            int minAge = getValueAt(rs, this.alias+"minAge", ctx, int.class);
            Image image = mapToIfPresent(Image.class, rs, ctx);
            String specification = getValueAt(rs, this.alias+"specification", ctx, String.class);
            float weight = getValueAt(rs, this.alias+"weight", ctx, float.class);
            boolean status = getValueAt(rs, this.alias+"status", ctx, boolean.class);
            String brand = getValueAt(rs, this.alias+"brand", ctx, String.class);
            String description = getValueAt(rs, this.alias+"description", ctx, String.class);
            Producer producer = mapToIfPresent(Producer.class, rs, ctx);
            Category category = mapToIfPresent(Category.class, rs, ctx);
            Discount discount = mapToIfPresent(Discount.class, rs, ctx);
            Blog blog = mapToIfPresent(Blog.class, rs, ctx);
            LocalDateTime createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
            LocalDateTime updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
            product = new Product(id, name, price, quantity, minAge, image,
                    specification, weight, status, brand, description,
                    producer, category, discount, blog, createAt, updateAt);
        } catch (SQLException e) {
            e.printStackTrace();
            product = null;
        }
        return product;
    }

}
