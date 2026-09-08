package com.haatech.orderservice.dto;

public record UserResponse(
        Long id,
        String name,
        String email,
        String phone
) {
}