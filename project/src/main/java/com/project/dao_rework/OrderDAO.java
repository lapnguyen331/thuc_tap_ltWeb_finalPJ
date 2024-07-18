package com.project.dao_rework;

import com.project.models_rework.Order;
import com.project.models_rework.Product;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RegisterBeanMappers({
        @RegisterBeanMapper(Order.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface OrderDAO {

}
