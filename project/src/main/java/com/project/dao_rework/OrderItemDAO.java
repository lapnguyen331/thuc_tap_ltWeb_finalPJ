package com.project.dao_rework;

import com.project.models_rework.OrderItem;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.time.LocalDateTime;
import java.util.List;

@RegisterBeanMappers({
        @RegisterBeanMapper(OrderItem.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface OrderItemDAO {
    @SqlQuery("""
            SELECT * FROM order_items
            WHERE
                orderItemId = :orderItemId
            """)
    List<OrderItem> getById_all(@Bind("orderItemId") Integer orderItemId);

    @SqlQuery("""
            SELECT oi.* FROM order_items oi
            JOIN sku_history_order hr
            ON oi.id = hr.orderItemId
            WHERE hr.skuHistoryId = :skuHistoryId
            """)
    List<OrderItem> getBySKUHistoryId_all(@Bind("skuHistoryId") Integer skuHistoryId);
}
