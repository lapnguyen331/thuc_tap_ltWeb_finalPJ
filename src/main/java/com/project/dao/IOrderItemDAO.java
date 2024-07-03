package com.project.dao;

import com.project.models.Order;
import com.project.models.OrderItem;

import java.util.List;

public interface IOrderItemDAO {
    List<OrderItem> getAll();
    int insert(OrderItem orderItem);
}
