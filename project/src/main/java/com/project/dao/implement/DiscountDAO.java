package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.ICategoryDAO;
import com.project.dao.IDiscountDAO;
import com.project.mappers.BlogRowMapper;
import com.project.mappers.CategoryRowMapper;
import com.project.mappers.DiscountRowMapper;
import com.project.mappers.ImageRowMapper;
import com.project.models.Category;
import com.project.models.Discount;
import org.jdbi.v3.core.Handle;

import java.util.List;

public class DiscountDAO extends AbstractDAO<Discount> implements IDiscountDAO {

    public DiscountDAO(Handle handle) {
        super(handle);
    }

    @Override
    public List<Discount> selectAll() {
        final String SELECT_ALL = "SELECT * FROM <table>";
        return query(SELECT_ALL, Discount.class, (query -> {
            query.define("table", "discounts");
        }), new DiscountRowMapper());
    }

    @Override
    public Discount findOneById(int id) {
        final String SELECT_ONE = "SELECT * FROM <table> d WHERE <col> = <para>";
        var result = query(SELECT_ONE, Discount.class, (query -> {
            query.define("table", "discounts")
                    .define("col", "d.id")
                    .define("para", id);
        }), new DiscountRowMapper("d"));
        if (result.isEmpty()) return null;
        return result.get(0);
    }

    public static void main(String[] args) {

    }
}