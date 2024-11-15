package com.RegistrationAndPaymentSystem.repository;

import com.RegistrationAndPaymentSystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUser_UserId(Long userId);  // Method to find payments by userId
}
