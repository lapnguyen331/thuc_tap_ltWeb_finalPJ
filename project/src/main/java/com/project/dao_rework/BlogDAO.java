package com.project.dao_rework;

import com.project.models_rework.Blog;
import com.project.models_rework.Product;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;

@RegisterBeanMappers({
        @RegisterBeanMapper(BlogDAO.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface BlogDAO {
    @SqlUpdate("""
            INSERT INTO blogs
                (userId, title, status, description, path,
                thumbnail, createAt, updateAt)
            VALUES
                (:b.userId, :b.title, :b.status, :b.description,
                :b.path, :b.thumbnail, :b.createAt, :b.updateAt)
            """)
    @GetGeneratedKeys
    Integer insert(@BindBean("b")Blog blog);

    @SqlUpdate("""
            UPDATE blogs SET
                path = :path,
                updateAt = now()
            WHERE
                id = :blogId
            """)
    void updatePath(@Bind("blogId") Integer blogId, @Bind("path") String path);
}
