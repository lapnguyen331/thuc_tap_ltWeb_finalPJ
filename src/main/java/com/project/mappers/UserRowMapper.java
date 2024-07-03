package com.project.mappers;

import com.project.models.Image;
import com.project.models.User;
import org.jdbi.v3.core.statement.StatementContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

public class UserRowMapper extends ARowMapper<User> {
    public UserRowMapper(String alias) {
        super(alias);
    }

    public UserRowMapper() {
    }

    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        int id = getValueAt(rs, this.alias+"id", ctx, int.class);
        String username = getValueAt(rs, this.alias+"username", ctx, String.class);
        String password = getValueAt(rs, this.alias+"password", ctx, String.class);
        Image avatar = mapToIfPresent(Image.class, rs, ctx);
        int levelAccess = getValueAt(rs, this.alias+"levelAccess", ctx, int.class);
        String firstName = getValueAt(rs, this.alias+"firstName", ctx, String.class);
        String lastname = getValueAt(rs, this.alias+"lastName", ctx, String.class);
        boolean gender = getValueAt(rs, this.alias+"gender", ctx, boolean.class);
        String address = getValueAt(rs, this.alias+"address", ctx, String.class);
        String phone = getValueAt(rs, this.alias+"phone", ctx, String.class);
        LocalDate birth = getValueAt(rs, this.alias+"birth", ctx, LocalDate.class);
        int status = getValueAt(rs, this.alias+"status", ctx, int.class);
        String email = getValueAt(rs, this.alias+"email", ctx, String.class);
        boolean verified = getValueAt(rs, this.alias+"verified", ctx, boolean.class);
        LocalDateTime createAt = getValueAt(rs, this.alias+"createAt", ctx, LocalDateTime.class);
        LocalDateTime updateAt = getValueAt(rs, this.alias+"updateAt", ctx, LocalDateTime.class);
        String token = getValueAt(rs, this.alias+"token", ctx, String.class);
        LocalDateTime tokenCreateAt = getValueAt(rs, this.alias+"tokenCreateAt", ctx, LocalDateTime.class);
        var user = new User(id, username, password, avatar, levelAccess, firstName, lastname, gender, address, phone, birth  == null ? null : Date.valueOf(birth), status, email, verified, createAt, updateAt);
        user.setToken(token);
        user.setTokenCreateAt(tokenCreateAt);
        return user;
    }
}
