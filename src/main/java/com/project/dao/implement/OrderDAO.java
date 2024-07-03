package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.IOrderDAO;
import com.project.mappers.OrderRowMapper;
import com.project.mappers.UserRowMapper;
import com.project.models.Order;
import com.project.models.User;
import org.jdbi.v3.core.Handle;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {

    public OrderDAO(Handle handle) {
        super(handle);
    }

    @Override
    public List<Order> getAll() {
        final String SELECT = "SELECT <columns> FROM <table1> od JOIN <table2> u ON" +
                " od.userId = u.id";
        return query(SELECT, Order.class, query -> {
            query.define("table1", "orders")
                    .define("table2", "users")
                    .define("columns", "od.*, u.id");
        }, new OrderRowMapper("od"), new UserRowMapper("u"));
    }

    @Override
    public int insert(Order order) {
        var keys = Arrays.asList("userId",
                "totalPrice",
                "status",
                "receiverName",
                "receiverAddress",
                "receiverPhone",
                "receiverEmail",
                "createAt",
                "updateAt");
        var values = Arrays.asList(
            order.getUser() == null ? null : order.getUser().getId(),
            order.getTotalPrice(),
            order.getStatus(),
            order.getReceiverName(),
            order.getReceiverAddress(),
            order.getReceiverPhone(),
            order.getReceiverEmail(),
            order.getCreateAt() == null ? LocalDateTime.now() : order.getCreateAt(),
            order.getUpdateAt() == null ? LocalDateTime.now() : order.getUpdateAt()
        );
        String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>)";
        return insertAndReturnGeneratedKeys(INSERT, "id", update -> {
            update.define("table", "orders")
                    .defineList("columns", keys)
                    .bindList("values", values);
        }).mapTo(int.class).findOne().orElse(0);
    }

    @Override
    public int update(Order order) {
        Map<String, Object> map = new HashMap<>();
        {
            map.put("userId", order.getUser() == null ? null : order.getUser().getId());
            map.put("totalPrice", order.getTotalPrice());
            map.put("status", order.getStatus());
            map.put("receiverName", order.getReceiverName());
            map.put("receiverAddress", order.getReceiverAddress());
            map.put("receiverPhone", order.getReceiverPhone());
            map.put("receiverEmail", order.getReceiverEmail());
            map.put("createAt", order.getCreateAt() == null ? LocalDateTime.now() : order.getCreateAt());
            map.put("updateAt", order.getUpdateAt() == null ? LocalDateTime.now() : order.getUpdateAt());
        }
        var keyValues = map.entrySet();
        var values = keyValues.stream().map(e -> String.format("%s = '%s'", e.getKey(), e.getValue())).toList();
        final String UPDATE = "UPDATE <table> SET <values> WHERE id = :order.id";
        return update(UPDATE, (update ->  {
            update.define("table", "orders")
                    .define("values", String.join(", ", values))
                    .bindBean("order", order);
        }));
    }
    @Override
    public List<Order> getAllUserOrder(User user) {
        final String SELECT = "SELECT <columns> FROM <table1> od" +
                " left join <table3> c on od.userId = c.id where c.id = <col1> ";
        return query(SELECT, Order.class, query -> {
            query.define("table1", "orders")
                    .define("table3","users")
                    .define("col1",user.getId())
                    .define("columns", "od.*, c.id,c.username");
        }, new OrderRowMapper("od"), new UserRowMapper("c"));
    }

}
