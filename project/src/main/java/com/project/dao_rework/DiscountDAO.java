package com.project.dao_rework;
import com.project.models_rework.Discount;
import com.project.models_rework.Image;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RegisterBeanMappers({
        @RegisterBeanMapper(Discount.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface DiscountDAO {
    @SqlQuery("""
            SELECT discountPercent FROM discounts
            WHERE id = :discountId
            """)
    List<Float> getDiscountPercent(@Bind("discountId") Integer discountId);
}
