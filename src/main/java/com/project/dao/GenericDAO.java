package com.project.dao;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface GenericDAO<T> {
    List<T> query(String sql, RowMapper<T> mapper, Consumer<Query> callback);
    int insert(String sql, Consumer<Update> callback) throws Exception;
    boolean check(String sql, Predicate<Query> check) throws Exception;
}
