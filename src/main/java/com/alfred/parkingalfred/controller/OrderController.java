package com.alfred.parkingalfred.controller;

import com.alfred.parkingalfred.service.OrderService;
import com.alfred.parkingalfred.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResultVO getOrders() {
        return new ResultVO<List>(200,"Return orders", orderService.getOrders());
    }
    @GetMapping(value = "/orders",params = {"type","status"})
    public ResultVO getParcelsByStatus(@RequestParam String type,@RequestParam String status){
        return new ResultVO<List>(200,"Return filteredOrders", orderService.getOrdersByTypeAndStatus(Integer.valueOf(type),Integer.valueOf(status)));
    }

}
