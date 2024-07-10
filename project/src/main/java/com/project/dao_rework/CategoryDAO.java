package com.project.dao_rework;

import com.project.models_rework.Category;
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

@RegisterBeanMappers({
        @RegisterBeanMapper(Category.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface CategoryDAO {
    @SqlQuery("""
            SELECT id, name FROM categories
            """)
    List<Category> getAll_id_name();
}
