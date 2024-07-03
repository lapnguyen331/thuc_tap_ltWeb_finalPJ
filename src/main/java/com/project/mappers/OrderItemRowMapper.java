package com.project.mappers;

import com.project.models.Order;
import com.project.models.OrderItem;
import com.project.models.Product;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderItemRowMapper extends ARowMapper<OrderItem> {
    public OrderItemRowMapper(String alias) {
        super(alias);
    }

    public OrderItemRowMapper() {
    }

    @Override
    public OrderItem map(ResultSet rs, StatementContext ctx) throws SQLException {
        Order order = mapToIfPresent(Order.class, rs, ctx);
        Product product = mapToIfPresent(Product.class, rs, ctx);
        int quantity = getValueAt(rs, this.alias+"quantity", ctx, int.class);
        float price = getValueAt(rs, this.alias+"price", ctx, float.class);
        LocalDateTime createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
        LocalDateTime updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
        return new OrderItem(order, product, quantity, price, createAt, updateAt);
    }
}
