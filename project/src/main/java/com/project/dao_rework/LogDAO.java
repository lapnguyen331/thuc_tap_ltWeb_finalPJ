package com.project.dao_rework;


import com.project.models_rework.Product;
import com.project.models_rework.log.Log;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;
import java.util.List;

@RegisterBeanMappers({
        @RegisterBeanMapper(Log.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface LogDAO {
    @SqlUpdate("""
            INSERT INTO logs
                (level, message,logTime,ipAddress)
            VALUES
                (:level, :message,:logTime,:ipAddress)
            """)
    @GetGeneratedKeys
    int insert(@BindBean Log log);
    @SqlQuery("""
            SELECT * FROM logs
            ORDER BY logTime DESC 
            """)
    List<Log> getCurrLog();
    @SqlUpdate("""
            DELETE FROM logs
            WHERE id = :id
            """)
    int deleteById(@Bind("id") Integer id);
}
