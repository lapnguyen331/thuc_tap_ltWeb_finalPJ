package com.project.services;

import com.project.dao.IOrderDAO;
import com.project.dao.IProductDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.exceptions.NotEnoughQuantityException;
import com.project.exceptions.NotFoundProductException;
import com.project.models.*;
import org.jdbi.v3.core.Handle;

import java.util.List;

public class OrderService extends AbstractService {
    private final IOrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_ORDER);
    }

    public OrderService(Handle handle) {
        super(handle);
        this.orderDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_ORDER);
    }

    public int update(Order order) {
        return orderDAO.update(order);
    }

    public List<Order> getAll() {
        return orderDAO.getAll();
    }
    public int createOrder(List<CartItem> cartItems, Order order) throws NotEnoughQuantityException, NotFoundProductException {
        ProductService productService = new ProductService(this.handle);
        OrderItemService orderItemService = new OrderItemService(this.handle);
        int orderId = -1;
        begin();
//            Tạo order trước
        orderId = orderDAO.insert(order);
        order.setId(orderId);
//            Thêm orderItem tương ứng với cartItem
        float orderPrice = 0f;
        for (var item : cartItems) {
            int productId = item.getProduct().getId();
            var product = productService.getById(productId);
//                Tính giá tiền của orderItem
            float price = (product.getPrice() / 100 * (100 - product.getDiscount().getDiscountPercent()) * item.getQuantity());
            OrderItem orderItem = new OrderItem(order, product, item.getQuantity(), price, null, null);
            orderPrice += price;
            orderItemService.insert(orderItem);
        }
//            Update lại thông tin tổng tiền của order
        order.setTotalPrice(orderPrice);
        update(order);
        return orderId;
    }
    //lấy order theo user
    public List<Order> getAllOrderOfUser(User user){
        return orderDAO.getAllUserOrder(user);
    }
    public Order getOrderById(int id) {
        List<Order> em = orderDAO.getAll();
        for(Order e :em){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        OrderService t = new OrderService();
        User em = new User();
//        em.setId(1);
//        t.getAllOrderOfUser(em).forEach(System.out::println);
        System.out.println(t.getOrderById(30));
    }
}
