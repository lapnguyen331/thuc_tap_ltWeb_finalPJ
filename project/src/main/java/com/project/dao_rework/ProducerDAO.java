package com.project.dao_rework;

import com.project.models_rework.Producer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.time.LocalDateTime;
import java.util.List;

@RegisterBeanMappers({
        @RegisterBeanMapper(Producer.class),
        @RegisterBeanMapper(LocalDateTime.class),
})
public interface ProducerDAO {
    @RegisterBeanMapper(Producer.class)
    @SqlQuery("""
            SELECT * FROM producers
            WHERE
                id = :producerId
            """)
    List<Producer> getById_all(@Bind("producerId") Integer producerId);

    @RegisterBeanMapper(Producer.class)
    @SqlQuery("""
            SELECT id, name FROM producers
            """)
    List<Producer> getAll_id_name();

    @RegisterBeanMapper(Producer.class)
    @SqlQuery("""
            SELECT id FROM producers
            WHERE
                id = :producerId
            """)
    List<Producer> checkExistsById(@Bind("producerId") Integer producerId);
}
