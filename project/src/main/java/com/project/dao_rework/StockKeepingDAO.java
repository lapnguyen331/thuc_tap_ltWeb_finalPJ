package com.project.dao_rework;

import com.project.models_rework.Blog;
import com.project.models_rework.StockKeeping;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.mapper.ColumnMappers;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.sqlobject.config.*;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RegisterBeanMapper(StockKeeping.class)
public interface StockKeepingDAO {
    @SqlQuery("""
            SELECT * FROM stock_keeping           
            """)
    List<StockKeeping> getAll_all();

    @SqlQuery("""
            SELECT * FROM stock_keeping
            WHERE id = :stockId
            """)
    List<StockKeeping> getById_all(@Bind("stockId") Integer stockId);

    @RegisterBeanMapper(LocalDate.class)
    @SqlUpdate("""
            INSERT INTO stock_keeping
                (productId, expiredDate, inStock, unitPrice, producerId)
            VALUES
                (:st.productId, :st.expiredDate, :st.inStock, :st.unitPrice, :st.producerId)
            """)
    @GetGeneratedKeys
    Integer insert(@BindBean("st") StockKeeping stockKeeping);

    @SqlUpdate("""
            UPDATE stock_keeping SET
                inStock = :newStock
            WHERE
                id = :stockId
            """)
    Integer updateInStockById(@Bind("stockId") Integer stockId, @Bind("newStock") Integer newStock);

    @SqlQuery("""
            SELECT inStock FROM stock_keeping
            WHERE 
                id = :stockId           
            """)
    Integer getInStockById(@Bind("stockId") Integer stockId);

    @SqlQuery("""
            SELECT productId FROM stock_keeping
            WHERE 
                id = :stockId
            """)
    Integer getProductIdById(@Bind("stockId") Integer stockId);

    @SqlQuery("""
            SELECT expiredDate FROM stock_keeping
            WHERE 
                id = :stockId           
            """)
    @RegisterColumnMapper(LocalDateColumnMapper.class)
    LocalDate getExpiredDateById(@Bind("stockId") Integer stockId);

    @SqlQuery("""
            SELECT id FROM stock_keeping
            WHERE 
                id = :stockId           
            """)
    List<StockKeeping> checkExistsById(@Bind("stockId") Integer stockId);

    class LocalDateColumnMapper implements ColumnMapper<LocalDate> {
        @Override
        public LocalDate map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
            final Date date = r.getDate(columnNumber);
            return date == null ? null : date.toLocalDate();
        }
    }
}
