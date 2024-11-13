package com.RegistrationAndPaymentSystem.repository;

import com.RegistrationAndPaymentSystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByUserId(Long userId);
}

