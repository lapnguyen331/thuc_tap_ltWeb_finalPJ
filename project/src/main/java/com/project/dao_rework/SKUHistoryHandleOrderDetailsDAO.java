package com.project.dao_rework;

import com.project.models_rework.SKUHistory_Handle_Order;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;
import java.util.List;

public interface SKUHistoryHandleOrderDetailsDAO {
    @SqlUpdate("""
            INSERT INTO sku_history_handle_order
                (skuHistoryId, orderId, productId, stockId, revenue, quantity)
            VALUES
                (:h.skuHistoryId, :h.orderId, :h.productId, :h.stockId, :h.revenue, :h.quantity)
            """)
    Integer insert(@BindBean("h") SKUHistory_Handle_Order handle);

    @SqlQuery("""
            SELECT IFNULL(SUM(quantity), 0) FROM sku_history_handle_order
            WHERE
                productId = :productId
            AND
                orderId = :orderId
            """)
    Integer getTotalQuantity(@Bind("productId") Integer productId,
                             @Bind("orderId") Integer orderId);

    @SqlQuery("""
            SELECT DISTINCT orderId, productId, stockId FROM 
                sku_history_handle_order
            WHERE
                orderId = :orderId
            """)
    @RegisterBeanMapper(SKUHistory_Handle_Order.class)
    List<SKUHistory_Handle_Order> getByOrderId_orderId_productId_stockId(@Bind("orderId") Integer orderId);

    @SqlQuery("""
            SELECT * FROM 
                sku_history_handle_order
            WHERE
                orderId = :orderId
            AND
                productId = :productId
            AND
                stockId = :stockId
            """)
    @RegisterBeanMapper(SKUHistory_Handle_Order.class)
    List<SKUHistory_Handle_Order> getByOrderIdAndProductIdAndStockId_all(@Bind("orderId") Integer orderId,
                                                               @Bind("productId") Integer productId,
                                                                         @Bind("stockId") Integer stockId);
}
