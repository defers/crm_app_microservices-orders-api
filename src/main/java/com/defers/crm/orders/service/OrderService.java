package com.defers.crm.orders.service;

import com.defers.crm.orders.dao.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll(Integer pageNumber, Integer numberOnPage);
    Order findById(int id);
    Order save(Order order);
}
