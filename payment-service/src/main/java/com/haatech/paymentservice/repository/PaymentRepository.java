package com.haatech.paymentservice.repository;

import com.haatech.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;




public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
