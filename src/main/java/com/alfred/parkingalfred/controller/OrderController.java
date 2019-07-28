package com.alfred.parkingalfred.controller;

import com.alfred.parkingalfred.service.OrderService;
import com.alfred.parkingalfred.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResultVO getOrders() {
        ResultVO resultVO = new ResultVO(200,"Return orders", orderService.getOrders());
        return resultVO;
    }
}
