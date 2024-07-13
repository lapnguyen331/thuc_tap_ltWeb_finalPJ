package com.project.dao_rework;

import com.project.models_rework.Producer;
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

import java.time.LocalDateTime;
import java.util.List;

@RegisterBeanMappers({
        @RegisterBeanMapper(Producer.class),
        @RegisterBeanMapper(LocalDateTime.class),
})
public interface ProducerDAO {
    @SqlQuery("""
            SELECT * FROM producers
            WHERE
                id = :producerId
            """)
    List<Producer> getById_all(@Bind("producerId") Integer producerId);

    @SqlQuery("""
            SELECT id, name FROM producers
            """)
    List<Producer> getAll_id_name();

    @SqlQuery("""
            SELECT id FROM producers
            WHERE
                id = :producerId
            """)
    List<Producer> checkExistsById(@Bind("producerId") Integer producerId);
}
