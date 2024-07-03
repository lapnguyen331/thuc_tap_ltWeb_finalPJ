package com.project.mappers;

import com.project.models.Order;
import com.project.models.User;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderRowMapper extends ARowMapper<Order> {
    public OrderRowMapper(String alias) {
        super(alias);
    }

    public OrderRowMapper() {
    }

    @Override
    public Order map(ResultSet rs, StatementContext ctx) throws SQLException {
        int id =  getValueAt(rs, this.alias+"id", ctx, int.class);
        User user = mapToIfPresent(User.class, rs, ctx);
        float totalPrice = getValueAt(rs, this.alias+"totalPrice", ctx, float.class);
        int status = getValueAt(rs, this.alias+"status", ctx, int.class);
        String receiverName = getValueAt(rs, this.alias+"receiverName", ctx, String.class);
        String receiverAddress = getValueAt(rs, this.alias+"receiverAddress", ctx, String.class);
        String receiverPhone = getValueAt(rs, this.alias+"receiverPhone", ctx, String.class);
        String receiverEmail = getValueAt(rs, this.alias+"receiverEmail", ctx, String.class);
        LocalDateTime createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
        LocalDateTime updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
        return new Order(id, user, totalPrice, status, receiverName, receiverAddress, receiverPhone, receiverEmail, createAt, updateAt);
    }
}
