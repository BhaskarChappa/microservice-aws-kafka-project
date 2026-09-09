package com.haatech.orderservice.service;



import com.haatech.orderservice.client.UserClient;
import com.haatech.orderservice.dto.UserResponse;
import com.haatech.orderservice.entity.Order;
import com.haatech.orderservice.event.OrderCreatedEvent;
import com.haatech.orderservice.producer.OrderEventProducer;
import com.haatech.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final OrderEventProducer orderEventProducer;

    public Order createOrder(Order order) {


        UserResponse userResponse = userClient.getUserById(order.getUserId());
              log.info(userResponse.toString());

        if(userResponse==null){
            throw new RuntimeException("user id not found");
        }
        order.setStatus("CREATED");

        Order savedOrder =  orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                UUID.randomUUID().toString(),
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getAmount(),
                "ORDER_CREATED"
        );

        orderEventProducer.publishOrderCreated(event);


        return savedOrder;

    }








    public Order getOrder(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + id));
    }
}
