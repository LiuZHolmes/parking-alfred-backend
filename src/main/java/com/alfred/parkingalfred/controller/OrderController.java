package com.alfred.parkingalfred.controller;

import com.alfred.parkingalfred.entity.Order;
import com.alfred.parkingalfred.enums.ResultEnum;
import com.alfred.parkingalfred.service.OrderService;
import com.alfred.parkingalfred.vo.ResultVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResultVO getOrders() {
        return new ResultVO<>(ResultEnum.SUCCESS.getStatus(), null, orderService.getOrders());
    }

    @PostMapping("/orders")
    public ResultVO createOrder(@RequestBody Order order) {
        return new ResultVO<>(ResultEnum.SUCCESS.getStatus(), null, orderService.addOrder(order));
    }

    @GetMapping("/orders/{id}")
    public ResultVO getOrderById(@PathVariable Long id) {
        return new ResultVO<>(ResultEnum.SUCCESS.getStatus(), null, orderService.getOrderById(id));
    }
}
