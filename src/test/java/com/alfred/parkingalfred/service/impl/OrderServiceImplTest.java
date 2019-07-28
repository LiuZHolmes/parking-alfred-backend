package com.alfred.parkingalfred.service.impl;

import com.alfred.parkingalfred.entity.Car;
import com.alfred.parkingalfred.entity.Order;
import com.alfred.parkingalfred.enums.OrderTypeEnum;
import com.alfred.parkingalfred.repository.OrderRepository;
import com.alfred.parkingalfred.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void should_get_order_when_get_order_by_id() throws JsonProcessingException {
        Long id = 1L;
        Order order = new Order();
        order.setId(id);

        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        Order actualOrder = orderService.getOrderById(id);

        assertEquals(objectMapper.writeValueAsString(order), objectMapper.writeValueAsString(actualOrder));
    }
}