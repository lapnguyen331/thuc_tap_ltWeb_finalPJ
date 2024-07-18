package com.project.dao_rework;
import com.project.models_rework.User;
import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RegisterBeanMappers({
        @RegisterBeanMapper(User.class),
        @RegisterBeanMapper(LocalDate.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface UserDAO extends SqlObject {
    @SqlQuery("SELECT <columns> FROM users <where_clause>")
    List<User> getUser(@Define(value = "columns") String columns,
                       @Define(value = "where_clause") String whereClause);

    @SqlQuery("SELECT * FROM users")
    List<User> getAllUsers();

    @SqlQuery("SELECT * FROM users where id = :id")
    List<User> getUserById(@Bind("id") Integer id);

    @SqlQuery("SELECT * FROM users where username LIKE <username>")
    List<User> getUserByUsername(@Define("username") String username);
}
