package com.alfred.parkingalfred.service;

import com.alfred.parkingalfred.entity.Car;
import com.alfred.parkingalfred.entity.Order;
import com.alfred.parkingalfred.enums.OrderStatusEnum;
import com.alfred.parkingalfred.enums.OrderTypeEnum;
import com.alfred.parkingalfred.repository.OrderRepository;
import com.alfred.parkingalfred.service.impl.OrderServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class OrderServiceImplTest {

    private OrderService orderService;

    private OrderRepository orderRepository;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderServiceImpl(orderRepository);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void should_add_new_order_when_create_new_order() throws JsonProcessingException {
        Car car = new Car();
        car.setCarNumber("123456");
        Order order = new Order();
        order.setCar(car);
        order.setCustomerAddress("address");
        order.setReservationTime(new Date().getTime());
        order.setType(OrderTypeEnum.PARK_CAR.getCode());

        Mockito.when(orderRepository.save(any())).thenReturn(order);
        Order actualOrder = orderService.addOrder(order);

        assertEquals(objectMapper.writeValueAsString(order), objectMapper.writeValueAsString(actualOrder));
    }
    @Test
    public void should_return_order_List_when_get_orders_by_status_and_type() throws JsonProcessingException {
        Car car = new Car();
        car.setCarNumber("123456");
        Order order = new Order();
        order.setCar(car);
        order.setCustomerAddress("address");
        order.setReservationTime(new Date().getTime());
        order.setType(OrderTypeEnum.PARK_CAR.getCode());
        order.setStatus(OrderStatusEnum.WAIT_FOR_RECEIVE.getCode());
        List<Order> orderList=new ArrayList<>();
        orderList.add(order);
        Mockito.when(orderRepository.findOrdersByTypeAndStatus(OrderTypeEnum.PARK_CAR.getCode(),OrderStatusEnum.WAIT_FOR_RECEIVE.getCode())).thenReturn(orderList);
        List<Order> actualOrderList = orderService.getOrdersByTypeAndStatus(OrderTypeEnum.PARK_CAR.getCode(),OrderStatusEnum.WAIT_FOR_RECEIVE.getCode());

        assertEquals(orderList.size(), actualOrderList.size());
    }
}