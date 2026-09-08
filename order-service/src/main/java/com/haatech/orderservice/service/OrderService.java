package com.haatech.orderservice.service;



import com.haatech.orderservice.entity.Order;
import com.haatech.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(Order order) {

        order.setStatus("CREATED");

        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + id));
    }
}
