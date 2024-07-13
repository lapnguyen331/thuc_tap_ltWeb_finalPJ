package com.project.dao_rework;
import com.project.models_rework.Product;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMappers;
import org.jdbi.v3.sqlobject.customizer.*;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RegisterBeanMappers({
        @RegisterBeanMapper(Product.class),
        @RegisterBeanMapper(LocalDateTime.class)
})
public interface ProductDAO {
    @SqlQuery("""
            SELECT * FROM products
            WHERE categoryId = :categoryId
            """)
    List<Product> getByCategoryId(@Bind("categoryId") Integer categoryId);

    @SqlQuery("""
            SELECT id FROM products
            WHERE id = :productId
            """)
    Optional<Product> checkExistByProductId(@Bind("productId") Integer productId);

    @SqlQuery("""
            SELECT * FROM products
            ORDER BY name DESC LIMIT :top
            """)
    List<Product> getTop(@Bind("top") Integer top);

    @SqlQuery("""
            SELECT * FROM products
            WHERE id = :productId
            """)
    List<Product> getById_all(@Bind("productId") Integer productId);

    @SqlQuery("""
            SELECT id, name, thumbnail FROM products
            WHERE 
                name LIKE <query>
            """)
    List<Product> getByName_id_name_thumbnail(@Define("query") String query);

    @SqlQuery("""
            SELECT id, name, thumbnail FROM products
            WHERE id = :productId
            """)
    List<Product> getById_id_name_thumbnail(@Bind("productId") Integer productId);

    @SqlUpdate("""
            INSERT INTO products
                (name, price, quantity, minAge, thumbnail,
                specification, status, brand, description,
                producerId, categoryId, discountId, blogId,
                weight, createAt, updateAt)
            VALUES
                (:p.name, :p.price, :p.quantity, :p.minAge, :p.thumbnail,
                :p.specification, :p.status, :p.brand, :p.description,
                :p.producerId, :p.categoryId, :p.discountId, :p.blogId,
                :p.weight, :p.createAt, :p.updateAt)
            """)
    @GetGeneratedKeys
    Integer insert(@BindBean("p") Product p);

    @SqlUpdate("""
            INSERT INTO product_galleries
                (productId, imageId)
            VALUES
                (:productId, :imageId)
            """)
    void insertToGallery(@Bind("productId") Integer productId,
                                  @Bind("imageId") Integer imageId);

    @SqlUpdate("""
            UPDATE products SET 
                thumbnail = :thumbnail, 
                updateAt = now() 
            WHERE id = :productId
            """)
    void changeThumbnail(@Bind("productId") Integer productId,
                         @Bind("thumbnail") Integer thumbnail);

    @SqlUpdate("""
            UPDATE products SET 
                blogId = :blogId, 
                updateAt = now() 
            WHERE id = :productId
            """)
    void changeBlog(@Bind("productId") Integer productId, @Bind("blogId") Integer blogId);
}
