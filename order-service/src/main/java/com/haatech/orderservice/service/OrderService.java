package com.haatech.orderservice.service;



import com.haatech.orderservice.client.UserClient;
import com.haatech.orderservice.dto.UserResponse;
import com.haatech.orderservice.entity.Order;
import com.haatech.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;

    public Order createOrder(Order order) {


        UserResponse userResponse = userClient.getUserById(order.getUserId());
              log.info(userResponse.toString());

        if(userResponse==null){
            throw new RuntimeException("user id not found");
        }


        order.setStatus("CREATED");

        return orderRepository.save(order);
    }








    public Order getOrder(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + id));
    }
}
