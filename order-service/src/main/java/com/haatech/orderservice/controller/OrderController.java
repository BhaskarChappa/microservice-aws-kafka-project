package com.haatech.orderservice.controller;

import com.haatech.orderservice.entity.Order;
import com.haatech.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {

        return orderService.getOrder(id);
    }
}
