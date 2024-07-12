package com.project.dao_rework;

import com.project.models_rework.Blog;
import com.project.models_rework.StockKeeping;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RegisterBeanMappers({
        @RegisterBeanMapper(StockKeeping.class),
        @RegisterBeanMapper(LocalDate.class)
})
public interface StockKeepingDAO {
    @SqlQuery("""
            SELECT * FROM stock_keeping           
            """)
    List<StockKeeping> getAll_all();

    @SqlUpdate("""
            INSERT INTO stock_keeping
                (productId, expiredDate, unitPrice)
            VALUES
                (:st.productId, :st.expiredDate, :st.unitPrice)
            """)
    @GetGeneratedKeys
    Integer insert(@BindBean("st") StockKeeping stockKeeping);
}
