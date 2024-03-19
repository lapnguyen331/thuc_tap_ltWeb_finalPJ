package com.project.dao;

import com.project.db.JDBIConnector;
import com.project.models.Image;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.QualifiedColumnMapperFactory;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.core.result.ResultBearing;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.SqlStatement;
import org.jdbi.v3.core.statement.Update;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
    private Handle handle;

    public AbstractDAO(Handle handle) {
        this.handle = handle;
    }

    public Handle getHandle() {
        return handle;
    }

    public <E> List<E> query(String sql, Class<E> tClass, Consumer<Query> callback, RowMapper<?>... mappers) {
        List<E> list = null;
        try {
            var query = handle.createQuery(sql);
            callback.accept(query);
            Arrays.stream(mappers).forEach(m -> query.registerRowMapper(m));
            list = query.mapTo(tClass).list();
//            System.out.println(query.getContext().getStatement());
            query.close();
        } catch (Exception e) {
            throw e;
        }
        return list;
    };

    private void setParameters(SqlStatement<Query> statement, Object... para) {
        for (int i = 0; i < para.length; i++) {
            statement.bind(i, para[i]);
        }
    }

    @Override
    public List<T> query(String sql, RowMapper<T> mapper, Consumer<Query> callback) {
        var query = handle.createQuery(sql);
        callback.accept(query);
        var list = query.map(mapper).list();
//        if (!handle.isClosed()) handle.close();
        return list;
    }

    @Override
    public int insert(String sql, Consumer<Update> callback) {
        int result = -1;
        try {
            var update = handle.createUpdate(sql);
            callback.accept(update);
            result = update.execute();
            update.close();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public int update(String sql, Consumer<Update> callback) {
        int result = 0;
        try {
            var update = handle.createUpdate(sql);
            callback.accept(update);
            result = update.execute();
//            System.out.println(update.getContext().getStatement());
            update.close();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public ResultBearing insertAndReturnGeneratedKeys(String sql, String column, Consumer<Update> callback) {
        ResultBearing result = null;
        try {
            var update = handle.createUpdate(sql);
            callback.accept(update);
            result = update.executeAndReturnGeneratedKeys(column);
//            System.out.println(update.getContext().getStatement());
            update.close();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    @Override
    public boolean check(String sql, Predicate<Query> check) {
        var query = handle.createQuery(sql);
        var rs = check.test(query);
//        if (!handle.isClosed()) handle.close();
        return rs;
    }
}
