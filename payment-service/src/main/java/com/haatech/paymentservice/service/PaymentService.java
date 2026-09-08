package com.haatech.paymentservice.service;


import com.haatech.paymentservice.entity.Payment;
import com.haatech.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {

        payment.setStatus("SUCCESS");

        return paymentRepository.save(payment);
    }

    public Payment getPayment(Long id) {

        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found with id: " + id));
    }
}