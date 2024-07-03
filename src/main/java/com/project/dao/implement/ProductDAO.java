package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.IProductDAO;
import com.project.mappers.*;
import com.project.models.Category;
import com.project.models.Product;
import org.jdbi.v3.core.Handle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class ProductDAO extends AbstractDAO<Product> implements IProductDAO {
    public ProductDAO(Handle handle) {
        super(handle);
    }

    @Override
    public List<Product> selectAll_fullDetails() {
        String SELECT_ALL = "SELECT * FROM <table1> p" +
                " LEFT JOIN <table2> t ON p.thumbnail = t.id" +
                " LEFT JOIN <table3> pr ON p.producerId = pr.id" +
                " LEFT JOIN <table4> c ON p.categoryId = c.id" +
                " LEFT JOIN <table5> d ON p.discountId = d.id" +
                " LEFT JOIN <table6> b ON p.blogId = b.id";
        return query(SELECT_ALL, Product.class, (query) -> {
            query.define("table1", "products")
                    .define("table2", "images")
                    .define("table3", "producers")
                    .define("table4", "categories")
                    .define("table5", "discounts")
                    .define("table6", "blogs");
        }, new ProductRowMapper("p"),
                new ImageRowMapper("t"),
                new ProducerRowMapper("pr"),
                new CategoryRowMapper("c"),
                new DiscountRowMapper("d"),
                new BlogRowMapper("b"));
    }

    @Override
    public List<Product> selectAll_shortDetails() {
        String SELECT = "SELECT <columns> FROM <table1> p" +
                " LEFT JOIN <table2> t ON p.thumbnail = t.id" +
                " LEFT JOIN <table3> d ON p.discountId = d.id";
        return query(SELECT, Product.class,
                (query) -> {
                    query.define("table1", "products");
                    query.define("table2", "images");
                    query.define("table3", "discounts");
                    query.defineList("columns", "p.id, p.name, p.description, t.path, d.discountPercent, d.discountPercent, p.price");
                },
                new ProductRowMapper("p"), new ImageRowMapper("t"), new DiscountRowMapper("d"));
    }

    @Override
    public List<Product> selectTop4_shortDetails() {
        String SELECT = "SELECT <columns> FROM <table1> p" +
                " LEFT JOIN <table2> t ON p.thumbnail = t.id" +
                " LEFT JOIN <table3> d ON p.discountId = d.id" +
                " ORDER BY p.name DESC LIMIT 4";
        return query(SELECT, Product.class,
                (query) -> {
                    query.define("table1", "products");
                    query.define("table2", "images");
                    query.define("table3", "discounts");
                    query.defineList("columns", "p.id, p.name, p.price, p.description, t.path, d.discountPercent");
                },
                new ProductRowMapper("p"), new ImageRowMapper("t"), new DiscountRowMapper("d"));
    }

    @Override
    public List<Product> selectTop3ProductsOf_shortDetails(Category category) {
        String SELECT = "SELECT <columns> FROM <table1> p" +
                " LEFT JOIN <table2> t ON p.thumbnail = t.id" +
                " LEFT JOIN <table3> d ON p.discountId = d.id" +
                " LEFT JOIN <table4> c ON p.categoryId = c.id" +
                " WHERE p.categoryId = :category.id" +
                " ORDER BY p.name DESC LIMIT 3";
        return query(SELECT, Product.class,
                (query) -> {
                    query.define("table1", "products")
                            .define("table2", "images")
                            .define("table3", "discounts")
                            .define("table4", "categories")
                            .defineList("columns", "p.id, p.name, p.price, p.description, t.path, d.discountPercent, c.id")
                            .bindBean("category", category);
                },
                new ProductRowMapper("p"), new ImageRowMapper("t"), new DiscountRowMapper("d"), new CategoryRowMapper("c"));
    }

    @Override
    public List<Product> selectTopProductsOf_shortDetails(Category category, int n) {
        String SELECT = "SELECT <columns> FROM <table1> p" +
                " LEFT JOIN <table2> t ON p.thumbnail = t.id" +
                " LEFT JOIN <table3> d ON p.discountId = d.id" +
                " WHERE p.categoryId = :c.id" +
                " ORDER BY p.name DESC LIMIT :n";
        return query(SELECT, Product.class,
                (query) -> {
                    query.define("table1", "products")
                            .define("table2", "images")
                            .define("table3", "discounts")
                            .defineList("columns", "p.id, p.name, p.price, p.description, t.path, d.discountPercent")
                            .bindBean("c", category)
                            .bind("n", n);
                },
                new ProductRowMapper("p"), new ImageRowMapper("t"), new DiscountRowMapper("d"));
    }
    @Override
    public List<Product> selectALLProductsOfCate_shortDetails(Category category) {
        String SELECT = "SELECT <columns> FROM <table1> p" +
                " LEFT JOIN <table2> t ON p.thumbnail = t.id" +
                " LEFT JOIN <table3> d ON p.discountId = d.id" +
                " WHERE p.categoryId = :c.id" ;
        return query(SELECT, Product.class,
                (query) -> {
                    query.define("table1", "products")
                            .define("table2", "images")
                            .define("table3", "discounts")
                            .defineList("columns", "p.id, p.name, p.price, p.description, p.specification,t.path, d.discountPercent")
                            .bindBean("c", category);
                },
                new ProductRowMapper("p"), new ImageRowMapper("t"), new DiscountRowMapper("d"));
    }

    @Override
    public List<String> selectAllBrands() {
        String SELECT_ALL = "SELECT DISTINCT <columns> FROM <table>";
        return query(SELECT_ALL, String.class, (query -> {
            query.define("columns", "brand")
                    .define("table", "products");
        }));
    }

    @Override
    public List<Product> searchProduct(String name, int categoryId, String brand, String minPrice, String maxPrice) {
        String filters;
        String discountPrice = "p.price * (100 - IFNULL(d.discountPercent, 0)) / 100";
        var values = Arrays.asList(
                (name != null) ? "p.name LIKE '%"+name+"%'" : "",
                (categoryId != -1 ) ? "p.categoryId = "+categoryId : "",
                (brand != null) ? "p.brand LIKE '%"+brand+"%'" : "",
                (minPrice != null) ? discountPrice+" >= "+minPrice : "",
                (maxPrice != null) ? discountPrice+" <= "+maxPrice : ""
        );
        values = values.stream().filter(v -> !v.isEmpty()).toList();
        if (!values.isEmpty()) {
            filters = "WHERE "+String.join(" AND ", values);
        } else {
            filters = "";
        }
        String SELECT = "SELECT <columns> FROM <table1> p" +
                " LEFT JOIN <table2> t ON p.thumbnail = t.id" +
                " LEFT JOIN <table3> d ON p.discountId = d.id" +
                " LEFT JOIN <table4> c ON p.categoryId = c.id" +
                " <filters>";
        return query(SELECT, Product.class, query -> {
            query.define("table1", "products")
                    .define("table2", "images")
                    .define("table3", "discounts")
                    .define("table4", "categories")
                    .define("columns", "p.name, p.id, p.price, d.discountPercent, p.brand, c.name, t.path")
                    .define("filters", filters);
        }, new ProductRowMapper("p"),
                new CategoryRowMapper("c"),
                new DiscountRowMapper("d"),
                new ImageRowMapper("t"));
    }

    @Override
    public List<Product> searchProduct(@Nullable String name, @NotNull List<Integer> categoryIds, @NotNull List<String> brands) {
        String filters;
        var mapCategoryIds = categoryIds.stream().map(c -> "p.categoryId = "+c).toList();
        var mapBrands = brands.stream().map(b -> "p.brand LIKE '%"+b+"%'").toList();
        var mapNames = new ArrayList<String>();
        if (name != null) mapNames.add("p.name LIKE '%"+name+"%'");
        var maps = Stream.of(mapNames, mapBrands, mapCategoryIds).flatMap(a -> a.stream()).toList();
        if (!maps.isEmpty()) {
            filters = "WHERE " + String.join(" OR ", maps);
        } else {
            System.out.println(maps);
            filters = "";
        }
        String SELECT = "SELECT <columns> FROM <table1> p" +
                " LEFT JOIN <table2> t ON p.thumbnail = t.id" +
                " LEFT JOIN <table3> c ON p.categoryId = c.id" +
                " <filters>";
        return query(SELECT, Product.class, query -> {
                    query.define("table1", "products")
                            .define("table2", "images")
                            .define("table3", "categories")
                            .define("columns", "p.name, p.id, p.price, p.quantity, p.specification, p.brand, c.name, t.path")
                            .define("filters", filters);
                }, new ProductRowMapper("p"),
                new CategoryRowMapper("c"),
                new DiscountRowMapper("d"),
                new ImageRowMapper("t"));
    }

    @Override
    public int insert(Product p) {
        var values = Arrays.asList(
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getMinAge(),
                p.getThumbnail() == null ? null : p.getThumbnail().getId(),
                p.getSpecification(),
                p.getWeight(),
                p.isStatus(),
                p.getBrand(),
                p.getDescription(),
                p.getProducer() == null ? null : p.getProducer().getId(),
                p.getCategory() == null ? null : p.getCategory().getId(),
                p.getDiscount() == null ? null : p.getDiscount().getId(),
                p.getBlog() == null ? null : p.getBlog().getId(),
                p.getCreateAt() == null ? LocalDateTime.now() : p.getCreateAt(),
                p.getUpdateAt() == null ? LocalDateTime.now() : p.getUpdateAt()
        );
        var columns = Arrays.asList(
                "name",
                "price",
                "quantity",
                "minAge",
                "thumbnail",
                "specification",
                "weight",
                "status",
                "brand",
                "description",
                "producerId",
                "categoryId",
                "discountId",
                "blogId",
                "createAt",
                "updateAt"
        );
        String INSERT = "INSERT INTO <table> (<columns>) VALUES (<values>)";
        return insertAndReturnGeneratedKeys(INSERT, "id", update -> {
            update.define("table", "products")
                    .defineList("columns", columns)
                    .bindList("values", values);
        }).mapTo(int.class).one();
    }

    @Override
    public int update(Product p) {
        var values = Arrays.asList(
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getMinAge(),
                p.getThumbnail() == null ? null : p.getThumbnail().getId(),
                p.getSpecification(),
                p.getWeight(),
                p.isStatus() ? 1 : 0,
                p.getBrand(),
                p.getDescription(),
                p.getProducer() == null ? null : p.getProducer().getId(),
                p.getCategory() == null ? null : p.getCategory().getId(),
                p.getDiscount() == null ? null : p.getDiscount().getId(),
                p.getBlog() == null ? null : p.getBlog().getId(),
                p.getCreateAt() == null ? LocalDateTime.now().toString() : p.getCreateAt().toString(),
                p.getUpdateAt() == null ? LocalDateTime.now().toString() : p.getUpdateAt().toString()
        );
        var columns = Arrays.asList(
                "name",
                "price",
                "quantity",
                "minAge",
                "thumbnail",
                "specification",
                "weight",
                "status",
                "brand",
                "description",
                "producerId",
                "categoryId",
                "discountId",
                "blogId",
                "createAt",
                "updateAt"
        );
        var updates = new ArrayList<String>();
        for (int i = 0; i < columns.size(); i++) {
            updates.add(String.format("%s = '%s'", columns.get(i), values.get(i)));
        }
        String INSERT = "UPDATE <table> SET <updates> WHERE id = :product.id";
        return update(INSERT, update -> {
            update.define("table", "products")
                    .define("updates", String.join(", ", updates))
                    .bindBean("product", p);
        });
    }

    @Override
    public boolean checkExists(int id) {
        String CHECK_EXIST_BY_ID = "SELECT id FROM products WHERE products.id = :id";
        return check(CHECK_EXIST_BY_ID, (query -> {
            query.bind("id", id);
            return query.mapTo(Integer.class).findOne().isPresent();
        }));
    }

    @Override
    public Product selectOne_fullDetails(int id) {
        String SELECT = "SELECT * FROM <table1> p" +
                " LEFT JOIN <table2> t ON p.thumbnail = t.id" +
                " LEFT JOIN <table3> pr ON p.producerId = pr.id" +
                " LEFT JOIN <table4> c ON p.categoryId = c.id" +
                " LEFT JOIN <table5> d ON p.discountId = d.id" +
                " LEFT JOIN <table6> b ON p.blogId = b.id" +
                " WHERE p.id = :id";
        var result =  query(SELECT, Product.class, (query) -> {
                    query.define("table1", "products")
                            .define("table2", "images")
                            .define("table3", "producers")
                            .define("table4", "categories")
                            .define("table5", "discounts")
                            .define("table6", "blogs")
                            .bind("id", id);
                }, new ProductRowMapper("p"),
                new ImageRowMapper("t"),
                new ProducerRowMapper("pr"),
                new CategoryRowMapper("c"),
                new DiscountRowMapper("d"),
                new BlogRowMapper("b"));
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Product selectOne_shortDetails(int id) {
        String SELECT = "SELECT <values> FROM <table1> p" +
                " LEFT JOIN <table2> t ON p.thumbnail = t.id" +
                " LEFT JOIN <table3> pr ON p.producerId = pr.id" +
                " LEFT JOIN <table4> c ON p.categoryId = c.id" +
                " LEFT JOIN <table5> d ON p.discountId = d.id" +
                " WHERE p.id = :id";
        var result =  query(SELECT, Product.class, (query) -> {
                    query.define("table1", "products")
                            .define("table2", "images")
                            .define("table3", "producers")
                            .define("table4", "categories")
                            .define("table5", "discounts")
                            .define("values", "p.*, t.path, d.discountPercent, c.name")
                            .bind("id", id);
                }, new ProductRowMapper("p"),
                new ImageRowMapper("t"),
                new ProducerRowMapper("pr"),
                new CategoryRowMapper("c"),
                new DiscountRowMapper("d"));
        return result.isEmpty() ? null : result.get(0);
    }
}
