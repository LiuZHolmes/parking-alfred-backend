package com.alfred.parkingalfred.service.impl;

import com.alfred.parkingalfred.entity.Order;
import com.alfred.parkingalfred.repository.OrderRepository;
import com.alfred.parkingalfred.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
}
