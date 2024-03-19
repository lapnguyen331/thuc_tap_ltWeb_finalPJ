package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.IOrderDAO;
import com.project.dao.IOrderItemDAO;
import com.project.exceptions.NotEnoughQuantityException;
import com.project.mappers.*;
import com.project.models.Order;
import com.project.models.OrderItem;
import com.project.services.ProductService;
import org.jdbi.v3.core.Handle;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderItemDAO extends AbstractDAO<OrderItem> implements IOrderItemDAO {

    public OrderItemDAO(Handle handle) {
        super(handle);
    }

    @Override
    public List<OrderItem> getAll() {
        final String SELECT = "SELECT <columns> FROM <table1> odi" +
                " JOIN <table2> od ON odi.orderId = od.id" +
                " JOIN <table3> p ON odi.productId = p.id "+
                "left join <table4> img on p.thumbnail = img.id"  ;
        return query(SELECT, OrderItem.class, query -> {
            query.define("table1", "order_details")
                    .define("table2", "orders")
                    .define("table3", "products")
                    .define("table4","images")
                    .define("columns", "odi.*, od.id, p.*,img.*");
        }, new OrderItemRowMapper("odi"), new OrderRowMapper("od"), new ProductRowMapper("p"),new ImageRowMapper("img"));
    }

    @Override
    public int insert(OrderItem orderItem) {
        var keys = Arrays.asList(
                "orderId",
                "productId",
                "quantity",
                "price",
                "createAt",
                "updateAt"
        );
        var values = Arrays.asList(
                orderItem.getOrder() == null ? null : orderItem.getOrder().getId(),
                orderItem.getProduct() == null ? null : orderItem.getProduct().getId(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getCreateAt() == null ? LocalDateTime.now() : orderItem.getCreateAt(),
                orderItem.getUpdateAt() == null ? LocalDateTime.now() : orderItem.getUpdateAt()
        );
        String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>)";
        return insertAndReturnGeneratedKeys(INSERT, "id", update -> {
            update.define("table", "order_details")
                    .defineList("columns", keys)
                    .bindList("values", values);
        }).mapTo(int.class).findOne().orElse(0);
    }


}
