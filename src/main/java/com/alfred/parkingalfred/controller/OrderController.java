package com.alfred.parkingalfred.controller;

import com.alfred.parkingalfred.service.OrderService;
import com.alfred.parkingalfred.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResultVO getOrders() {
        return new ResultVO<List>(200,"Return orders", orderService.getOrders());
    }
}
