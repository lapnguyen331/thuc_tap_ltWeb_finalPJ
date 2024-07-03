package com.project.mappers;

import com.project.models.Image;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.mapper.RowMapperFactory;
import org.jdbi.v3.core.statement.StatementContext;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class ARowMapper<T> implements RowMapper<T> {
    String alias;

    public ARowMapper(String alias) {
        this.alias = alias+".";
    }

    public ARowMapper() {
        this.alias = "";
    }

    protected <T> T getValueAt (ResultSet rs, String label, StatementContext ctx, Class<T> tClass) {
        T value = null;
        try {
//            this statement will throw SQLException
            rs.findColumn(label);
            ColumnMapper<T> columnMap = ctx.findColumnMapperFor(tClass).orElse(null);

//            this statement will throw SQLException
            if (columnMap != null) value = columnMap.map(rs, label, ctx);
        } catch (SQLException e) {
//            handle SQLException here
//            e.printStackTrace();
            if (tClass.isPrimitive() || isWrapperClass(tClass)) {
                value = (T) Array.get(Array.newInstance(getPrimitive(tClass), 1), 0);
            }
        }
        return value;
    }

    public <E> E mapToIfPresent(Class<E> clazz, ResultSet rs, StatementContext ctx) throws SQLException {
        var mapper = ctx.findRowMapperFor(clazz);
        if (mapper.isPresent()) return mapper.get().map(rs, ctx);
        else
            return null;
    }

    private boolean isWrapperClass(Class<?> clazz) {
        return clazz == Boolean.class ||
                clazz == Integer.class ||
                clazz == Character.class ||
                clazz == Byte.class ||
                clazz == Short.class ||
                clazz == Double.class ||
                clazz == Long.class ||
                clazz == Float.class;
    }

    private Class<?> getPrimitive(Class<?> clazz) {
        if (isWrapperClass(clazz)) {
            if (clazz == Boolean.class) return boolean.class;
            else if (clazz == Integer.class) return int.class;
            else if (clazz == Character.class) return char.class;
            else if (clazz == Byte.class) return byte.class;
            else if (clazz == Short.class) return short.class;
            else if (clazz == Double.class) return double.class;
            else if (clazz == Long.class) return long.class;
            else if (clazz == Float.class) return float.class;
        }
        return clazz;
    }

    @Override
    public RowMapper<T> specialize(ResultSet rs, StatementContext ctx) throws SQLException {
        return RowMapper.super.specialize(rs, ctx);
    }

    @Override
    public void init(ConfigRegistry registry) {
        RowMapper.super.init(registry);
    }
 }
