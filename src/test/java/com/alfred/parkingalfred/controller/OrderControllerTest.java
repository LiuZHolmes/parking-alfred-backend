package com.alfred.parkingalfred.controller;

import com.alfred.parkingalfred.entity.Order;
import com.alfred.parkingalfred.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private List<Order> orders;

    @Before
    public void setUp() {
        orders = new ArrayList<>();
        orders.add(new Order((long)1, 1,"南方软件园", 1));
        orders.add(new Order((long)2, 1,"凤凰兰亭", 2));
        orders.add(new Order((long)3, 1,"红树东岸", 3));
    }

    @Test
    public void should_return_orders_when_get_it() throws Exception {
        when(orderService.getOrders()).thenReturn(orders);

        mockMvc.perform(get("/orders"))
                .andExpect(jsonPath("$.data.length()")
                        .value(3));
    }
    @Test
    public void should_return_orders_when_get_it_by_type_and_status() throws Exception {
        List<Order>newOrders = new ArrayList<>();
        newOrders.add(new Order((long)1, 1,"南方软件园", 1));
        when(orderService.getOrdersByTypeAndStatus(any(Integer.class),any(Integer.class))).thenReturn(newOrders);
        mockMvc.perform(get("/orders").param("type","1").param("status","1"))
                .andExpect(jsonPath("$.data.length()")
                        .value(1));
    }
}
