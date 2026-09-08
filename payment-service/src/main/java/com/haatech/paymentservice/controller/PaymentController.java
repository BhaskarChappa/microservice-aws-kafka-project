package com.haatech.paymentservice.controller;



import com.haatech.paymentservice.entity.Payment;
import com.haatech.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {

        return paymentService.createPayment(payment);
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {

        return paymentService.getPayment(id);
    }
}
