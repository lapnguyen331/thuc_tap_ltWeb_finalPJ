package com.project.dao.implement;

import com.project.dao.*;
import com.project.db.JDBIConnector;
import org.jdbi.v3.core.Handle;

public abstract class FactoryDAO {
    public static final Class<IProductDAO> DAO_PRODUCT = IProductDAO.class;
    public static final Class<IImageDAO> DAO_IMAGE = IImageDAO.class;
    public static final Class<IBlogDAO> DAO_BLOG = IBlogDAO.class;
    public static final Class<ICategoryDAO> DAO_CATEGORY = ICategoryDAO.class;
    public static final Class<IUserDAO> DAO_USER = IUserDAO.class;
    public static final Class<IOrderDAO> DAO_ORDER = IOrderDAO.class;
    public static final Class<IOrderItemDAO> DAO_ORDER_ITEM = IOrderItemDAO.class;
    public static final Class<IRatingDAO> DAO_RATING = IRatingDAO.class;
    public static final Class<IProducerDAO> DAO_PRODUCER = IProducerDAO.class;
    public static <T> T getDAO(Handle handle, Class<T> clazz) {
        if (clazz == DAO_PRODUCT) return (T) new ProductDAO(handle);
        else if (clazz == DAO_IMAGE) return (T) new ImageDAO(handle);
        else if (clazz == DAO_BLOG) return (T) new BlogDAO(handle);
        else if (clazz == DAO_CATEGORY) return (T) new CategoryDAO(handle);
        else if (clazz == DAO_USER) return (T) new UserDAO(handle);
        else if (clazz == DAO_ORDER) return (T) new OrderDAO(handle);
        else if (clazz == DAO_ORDER_ITEM) return (T) new OrderItemDAO(handle);
        else if (clazz == DAO_RATING) return (T) new RatingDAO(handle);
        else if (clazz == DAO_PRODUCER) return (T) new ProducerDAO(handle);
        return null;
    }
}
