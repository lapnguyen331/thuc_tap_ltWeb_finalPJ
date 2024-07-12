package com.project.dao_rework;

import com.project.models_rework.SKUHistory;
import com.project.models_rework.enums.SKUChangeType;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RegisterBeanMappers({
        @RegisterBeanMapper(SKUHistory.class),
        @RegisterBeanMapper(LocalDateTime.class),
        @RegisterBeanMapper(SKUChangeType.class)
})
public interface SKUHistoryDAO {
    @SqlUpdate("""
            INSERT INTO sku_history
                (stockId, changeValue, type, note, createAt)
            VALUES
                (:st.stockId, :st.changeValue, :st.type, :st.note, :st.createAt)
            """)
    @GetGeneratedKeys
    Integer insert(@BindBean("st") SKUHistory stockKeepingHistory);


}
