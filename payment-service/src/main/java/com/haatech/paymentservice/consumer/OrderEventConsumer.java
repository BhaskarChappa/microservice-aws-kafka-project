package com.haatech.paymentservice.consumer;

import com.haatech.paymentservice.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventConsumer {



    @KafkaListener(
            topics = "order-event",
            groupId = "payment-service"
    )
    public void listenOrderCreated(OrderCreatedEvent event) {

        log.info(
                "Received OrderCreatedEvent: orderId={}, userId={}, amount={}",
                event.orderId(),
                event.userId(),
                event.amount()
        );
    }

}
