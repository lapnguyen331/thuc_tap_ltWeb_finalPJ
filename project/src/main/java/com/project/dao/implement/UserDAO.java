package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.IUserDAO;
import com.project.mappers.ImageRowMapper;
import com.project.mappers.UserRowMapper;
import com.project.models.Image;
import com.project.models.User;
import org.jdbi.v3.core.Handle;

import java.time.LocalDateTime;
import java.util.*;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {
    public UserDAO(Handle handle) {
        super(handle);
    }

    @Override
    public List<User> getAll() {
        final String SELECT = "SELECT <columns> FROM <table1> u LEFT JOIN <table2> i" +
                " ON u.avatar = i.id";
        return query(SELECT, User.class, (query -> {
           query.define("columns", "u.*, i.path")
                   .define("table1", "users")
                   .define("table2", "images");
        }), new UserRowMapper("u"), new ImageRowMapper("i"));
    }

    @Override
    public User getLoginInfo(String username) {
        final String SELECT = "SELECT <columns> FROM <table1> u LEFT JOIN <table2> i" +
                " ON u.avatar = i.id" +
                " WHERE u.username = :username";
        var rs = query(SELECT, User.class, (query -> {
            query.define("columns", "u.id, u.username, u.password, i.path, u.levelAccess, u.verified")
                    .define("table1", "users")
                    .define("table2", "images")
                    .bind("username", username);
        }), new UserRowMapper("u"), new ImageRowMapper("i"));
        if (rs.isEmpty()) return null;
        return rs.get(0);
    }

    @Override
    public User getInforUserById(int id) {
        final String SELECT = "SELECT <columns> FROM <table1> u LEFT JOIN <table2> i" +
                " ON u.avatar = i.id" +
                " WHERE u.id = :id";
        var rs = query(SELECT, User.class, (query -> {
            query.define("columns", " u.*,i.id,i.path")
                    .define("table1", "users")
                    .define("table2", "images")
                    .bind("id", id);
        }), new UserRowMapper("u"), new ImageRowMapper("i"));
        if (rs.isEmpty()) return null;
        return rs.get(0);
    }

    @Override
    public User getToken(User user) {
        final String SELECT = "SELECT <columns> FROM <table>" +
                " WHERE id = :user.id";
        var rs = query(SELECT, User.class, (query -> {
            query.defineList("columns", "token, tokenCreateAt, verified")
                    .define("table", "users")
                    .bindBean("user", user);
        }), new UserRowMapper());
        if (rs.isEmpty()) return null;
        return rs.get(0);
    }

    @Override
    public int updateAccount(int id,String username,String password ) {
        List<String> values = new ArrayList<>();
        values.add("username='"+username+"'");
        values.add("password='"+password+"'");
        final String UPDATE = "UPDATE <table> SET <values> WHERE id = <col1>";
        return update(UPDATE, (update ->  {
            update.define("table", "users")
                    .define("col1",id)
                    .define("values", String.join(", ", values));
        }));
    }

    @Override
    public int updateToken(int id, String token) {
        final String UPDATE = "UPDATE <table> SET token = :token, tokenCreateAt = :createAt WHERE id = :id";
        return update(UPDATE, (update ->  {
            update.define("table", "users")
                    .bind("token", token)
                    .bind("createAt", LocalDateTime.now())
                    .bind("id", id);
        }));
    }

    public int insert(User user) {
        var keys = Arrays.asList(
                "username",
                "password",
                "avatar",
                "levelAccess",
                "firstName",
                "lastName",
                "gender",
                "address",
                "phone",
                "birth",
                "status",
                "email",
                "verified",
                "createAt",
                "updateAt",
                "token",
                "tokenCreateAt"
        );
        var values = Arrays.asList(
                user.getUsername(),
                user.getPassword(),
                Optional.ofNullable(user.getAvatar()).map(Image::getId).orElse(1042),
                user.getLevelAccess(),
                user.getFirstName(),
                user.getLastName(),
                user.isGender(),
                user.getAddress(),
                user.getPhone(),
                user.getBirthDate(),
                user.getStatus(),
                user.getEmail(),
                user.isVerified(),
                Optional.ofNullable(user.getCreateAt()).orElse(LocalDateTime.now()),
                Optional.ofNullable(user.getUpdateAt()).orElse(LocalDateTime.now()),
                user.getToken(),
                user.getTokenCreateAt()
        );
        final String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>)";
        return insertAndReturnGeneratedKeys(INSERT, "id", (update -> {
            update.define("table", "users")
                    .defineList("columns", keys)
                    .bindList("values", values);
        })).mapTo(int.class).findOne().orElse(-1);
    }

    @Override
    public int validate(int id) {
        final String UPDATE = "UPDATE <table> SET verified = 1 WHERE id = :id";
        return update(UPDATE, (update -> {
            update.define("table", "users")
                    .bind("id", id);
        }));
    };
    public int updateInfor(int id, String username, String fistname,String lastname, String email, String phone,String address, String gender, String birth) {
        var keys = Arrays.asList(
                "username",
                "firstName",
                "lastName",
                "gender",
                "address",
                "phone",
                "birth",
                "email",
                "updateAt"
        );
        var values = Arrays.asList(
                username,
                fistname,
                lastname,
                gender,
                address,
                phone,
                birth,
                email,
                Optional.ofNullable(new User().getUpdateAt()).orElse(LocalDateTime.now())
        );
        List<String> con = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            con.add(keys.get(i)+ "='" +values.get(i)+"'");
        }
        final String UPDATE = "UPDATE <table> SET <values> WHERE id = <col1>";
        return update(UPDATE, (update ->  {
            update.define("table", "users")
                    .define("col1",id)
                    .define("values", String.join(", ", con));
        }));
    }
}
