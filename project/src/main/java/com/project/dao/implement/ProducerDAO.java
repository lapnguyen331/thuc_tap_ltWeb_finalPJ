package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.IProducerDAO;
import com.project.mappers.ProducerRowMapper;
import com.project.models.Producer;
import org.jdbi.v3.core.Handle;

import java.util.List;

public class ProducerDAO extends AbstractDAO<Producer> implements IProducerDAO {

    public ProducerDAO(Handle handle) {
        super(handle);
    }

    @Override
    public List<Producer> selectAll() {
        final String SELECT_ALL = "SELECT * FROM <table> p";
        return query(SELECT_ALL, Producer.class, (query -> {
            query.define("table", "producers");
        }), new ProducerRowMapper("p"));
    }

    @Override
    public Producer findOneById(int id) {
        final String SELECT_ONE = "SELECT * FROM <table> p WHERE <col> = <para>";
        var result = query(SELECT_ONE, Producer.class, (query -> {
            query.define("table", "producers")
                    .define("col", "p.id")
                    .define("para", id);
        }), new ProducerRowMapper("p"));
        if (result.isEmpty()) return null;
        return result.get(0);
    }

    public static void main(String[] args) {

    }
}
