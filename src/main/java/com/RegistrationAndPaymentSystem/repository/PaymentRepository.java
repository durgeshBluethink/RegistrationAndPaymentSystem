package com.RegistrationAndPaymentSystem.repository;

import com.RegistrationAndPaymentSystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
