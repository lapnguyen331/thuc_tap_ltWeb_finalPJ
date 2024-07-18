package com.project.mappers;

import com.project.models.Contact;
import com.project.models.Product;
import com.project.models.Rating;
import com.project.models.User;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ContactRowMapper extends ARowMapper<Contact> {
    public ContactRowMapper(String alias) {
        super(alias);
    }

    public ContactRowMapper() {
    }

    @Override
    public Contact map(ResultSet rs, StatementContext ctx) throws SQLException {
        int id = getValueAt(rs, this.alias+"id", ctx, int.class);
        String firstName = getValueAt(rs, this.alias+"firstName", ctx, String.class);
        String lastName = getValueAt(rs, this.alias+"lastName", ctx, String.class);
        String phone = getValueAt(rs, this.alias+"phone", ctx, String.class);
        String email = getValueAt(rs, this.alias+"email", ctx, String.class);

        int status = getValueAt(rs, this.alias+"status", ctx, int.class);
        String content = getValueAt(rs, this.alias+"content", ctx, String.class);
        LocalDateTime createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
        LocalDateTime updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
        return new Contact(id, firstName, lastName, phone, email, status,content,createAt, updateAt);
    }
}
