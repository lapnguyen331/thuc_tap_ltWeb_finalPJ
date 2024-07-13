package com.project.dao_rework;

import com.project.models_rework.SKUHistory;
import com.project.models_rework.enums.SKUChangeType;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RegisterBeanMappers({
        @RegisterBeanMapper(SKUHistory.class),
        @RegisterBeanMapper(LocalDateTime.class),
        @RegisterBeanMapper(SKUChangeType.class)
})
public interface SKUHistoryDAO {
    @SqlUpdate("""
            INSERT INTO sku_history
                (stockId, prevValue, changeValue, type, note, createAt)
            VALUES
                (:st.stockId, :st.prevValue, :st.changeValue, :st.type, :st.note, :st.createAt)
            """)
    @GetGeneratedKeys
    Integer insert(@BindBean("st") SKUHistory stockKeepingHistory);

    @SqlQuery("""
            SELECT * FROM sku_history sh
            WHERE
                sh.stockId = :stockId
            AND 
                MONTH(sh.createAt) = :month
            """)
    List<SKUHistory> getByCreateAtInMonth(@Bind("month") Integer month);

    @SqlQuery("""
            SELECT sku_history_order.orderItemId FROM sku_history
            LEFT JOIN sku_history_order
            ON 
                sku_history.id = sku_history_order.skuHistoryId
            WHERE 
                sku_history.id = :skuHistoryId
            """)
    Integer getRelatedOrderItemById(@Bind("skuHistoryId") Integer skuHistoryId);

    @SqlQuery("""
            SELECT * FROM sku_history sh
            JOIN sku_history_order sho
            ON 
                sh.id = sho.skuHistoryId
            WHERE
                sh.stockId = :stockId
            AND 
                MONTH(sh.createAt) = :month
            """)
    List<SKUHistory> getByRelatedWithOrderItemAndByStockIdAndInMonth_all(
            @Bind("stockId") Integer skuId,
            @Bind("month") Integer month);

    @SqlQuery("""
            SELECT sum(changeValue) FROM sku_history sh
            WHERE 
                sh.id IN (<skuHistoryIds>)
            """)
    Integer getTotalChangeValueBySkuHistoryIds(@BindList("skuHistoryIds") List<Integer> skuHistoryIds);

    @SqlQuery("""
            SELECT sum(revenue) FROM sku_history_order sho
            WHERE 
                sho.skuHistoryId IN (<skuHistoryIds>)
            """)
    Float getTotalRevenueBySkuHistoryIds(@BindList("skuHistoryIds") List<Integer> skuHistoryIds);

    @SqlQuery("""
            SELECT * FROM sku_history sh
            WHERE 
                sh.id = :stockId
            AND
                sh.createAt = (
                    SELECT MAX(createAt) FROM sku_history
                    WHERE
                        id = :stockId
                );
            """)
    SKUHistory getLatestHistoryByStockId(@Bind("stockId") Integer stockId);
}
