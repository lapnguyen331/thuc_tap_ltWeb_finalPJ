package com.project.services;

import com.project.dao.IOrderDAO;
import com.project.dao.IOrderItemDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.dao.implement.OrderItemDAO;
import com.project.exceptions.NotEnoughQuantityException;
import com.project.exceptions.NotFoundProductException;
import com.project.models.Order;
import com.project.models.OrderItem;
import org.jdbi.v3.core.Handle;

import java.util.List;

public class OrderItemService extends AbstractService {
    private final IOrderItemDAO orderItemDAO;

    public OrderItemService() {
        this.orderItemDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_ORDER_ITEM);
    }

    public OrderItemService(Handle handle) {
        super(handle);
        this.orderItemDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_ORDER_ITEM);
    }

    public List<OrderItem> getAll() {
        return orderItemDAO.getAll();
    }

    public int insert(OrderItem orderItem) throws NotEnoughQuantityException, NotFoundProductException {
        ProductService productService = new ProductService(this.handle);
        var product = productService.getById(orderItem.getProduct().getId());
        int leftAmount = product.getQuantity();
        int orderAmount = orderItem.getQuantity();
        if (orderAmount > leftAmount) throw new NotEnoughQuantityException(String.format("Xin lỗi, sản phẩm %s đã hết hàng.", product.getName()));
        product.setQuantity(leftAmount - orderAmount);
        productService.updateProduct(product);
        return orderItemDAO.insert(orderItem);
    }

    public static void main(String[] args) {
        var service = new OrderItemService();
        service.getAll().forEach(System.out::println);
    }
}
