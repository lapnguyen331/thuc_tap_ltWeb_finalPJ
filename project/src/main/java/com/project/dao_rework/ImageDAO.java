package com.project.dao_rework;
import com.project.models_rework.Image;
import com.project.models_rework.User;
import org.jdbi.v3.sqlobject.SqlObject;
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
        @RegisterBeanMapper(Image.class),
        @RegisterBeanMapper(LocalDate.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface ImageDAO {
    @SqlQuery("SELECT <columns> FROM images <where_clause>")
    List<Image> getImage(@Define(value = "columns") String columns,
                         @Define(value = "where_clause") String whereClause);

    @SqlQuery("SELECT * FROM images")
    List<Image> getAllImages();

    @SqlQuery("SELECT * FROM images WHERE id = :id")
    List<Image> getImageById(@Bind("id") Integer id);

    @SqlQuery("SELECT * FROM images WHERE id IN (<ids>)")
    List<Image> getImagesByIds(@BindList("ids") List<Integer> ids);

    @SqlQuery("""
        SELECT i.* FROM images i
        INNER JOIN product_galleries gl
        ON gl.imageId = i.id
        WHERE gl.productId = :productId
    """)
    List<Image> getImagesByProductId(@Bind("productId") Integer productId);

    @SqlUpdate("""
            INSERT INTO images
                (path, uuid, createAt, updateAt)
            VALUES
                (:i.path, :i.uuid, :i.createAt, :i.updateAt)
            """)
    @GetGeneratedKeys
    Integer insert(@BindBean("i") Image image);
}
