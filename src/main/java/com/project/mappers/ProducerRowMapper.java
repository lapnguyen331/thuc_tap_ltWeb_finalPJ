package com.project.mappers;

import com.project.models.Producer;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProducerRowMapper extends ARowMapper<Producer> {
    public ProducerRowMapper(String alias) {
        super(alias);
    }

    public ProducerRowMapper() {
    }


    @Override
    public Producer map(ResultSet rs, StatementContext ctx) throws SQLException {
        int id = getValueAt(rs, this.alias+"id", ctx, int.class);
        String name = getValueAt(rs, this.alias+"name", ctx, String.class);
        boolean status = getValueAt(rs, this.alias+"status", ctx, boolean.class);
        String address = getValueAt(rs, this.alias+"address", ctx, String.class);
        String email = getValueAt(rs, this.alias+"email", ctx, String.class);
        String phone = getValueAt(rs, this.alias+"phone", ctx, String.class);
        String taxCode = getValueAt(rs, this.alias+"taxCode", ctx, String.class);
        var createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
        var updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
        return new Producer(id, name, status, address, email, phone, taxCode, createAt, updateAt);
    }
}
