package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.OrderDto;
import com.bridgelabz.bookstoreapplication.entity.Order;

import java.util.List;

public interface OrderServiceInterface {
    String addOrder(OrderDto orderdto);

    List<Order> getAll();

    Order FindById(int id);

    void getOrderById(int id);

    String editById(int id, OrderDto orderDto);
}
