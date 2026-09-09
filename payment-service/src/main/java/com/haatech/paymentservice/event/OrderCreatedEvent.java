package com.haatech.paymentservice.event;


import java.math.BigDecimal;

public record OrderCreatedEvent(
        String eventId,
        Long orderId,
        Long userId,
        BigDecimal amount,
        String eventType
) {
}