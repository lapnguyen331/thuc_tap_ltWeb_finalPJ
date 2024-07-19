package com.project.dao_rework;

import com.project.models_rework.Order;
import com.project.models_rework.OrderDetails;
import com.project.models_rework.enums.OrderDetailsStatus;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@RegisterBeanMappers({
        @RegisterBeanMapper(OrderDetails.class),
})
public interface OrderDetailsDAO {
    @SqlQuery("""
            SELECT * FROM order_details
            WHERE 
                orderId = :orderId 
            AND 
                productId = :productId            
            """)
    List<OrderDetails> getDetailsByOrderIdAndProductId(@Bind("orderId") Integer orderId,
                                                       @Bind("productId") Integer productId);

    @SqlUpdate("""
            UPDATE order_details
            SET 
                status = :status
            WHERE 
                orderId = :orderId
            AND 
                productId = :productId            
            """)
    @RegisterFieldMapper(Integer.class)
    Integer setStatus(@Bind("orderId") Integer orderId,
                      @Bind("productId") Integer productId,
                      @Bind("status") OrderDetailsStatus status);

    @SqlQuery("""
            SELECT DISTINCT productId FROM order_details
            WHERE
                orderId = :orderId
            """)
    List<Integer> getById_productId(@Bind("orderId") Integer orderId);

    @SqlQuery("""
            SELECT DISTINCT productId FROM order_details
            WHERE
                orderId = :orderId
            AND status = :status
            """)
    List<Integer> getByIdAndStatus_productId(@Bind("orderId") Integer orderId,
                                             @Bind("status") OrderDetailsStatus status);

}
