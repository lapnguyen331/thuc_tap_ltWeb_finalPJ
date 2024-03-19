package com.project.mappers;

import com.project.models.Discount;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DiscountRowMapper extends ARowMapper<Discount> {
    public DiscountRowMapper(String alias) {
        super(alias);
    }

    public DiscountRowMapper() {
    }

    @Override
    public Discount map(ResultSet rs, StatementContext ctx) {
        Discount discount = null;
        int id = getValueAt(rs, this.alias+"id", ctx, int.class);
        String name = getValueAt(rs, this.alias+"name", ctx, String.class);
        float discountPercent = getValueAt(rs, this.alias+"discountPercent", ctx, float.class);
        boolean status = getValueAt(rs, this.alias+"status", ctx, boolean.class);
        var startAt = getValueAt(rs, this.alias+"startAt", ctx, LocalDateTime.class);
        var finishAt = getValueAt(rs, this.alias+"finishAt", ctx, LocalDateTime.class);
        var createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
        var updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
        discount = new Discount(id, name, discountPercent, status, startAt, finishAt, createAt, updateAt);
        return discount;
    }
}
