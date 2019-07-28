package com.alfred.parkingalfred.service;

import com.alfred.parkingalfred.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    Order addOrder(Order order);
}
