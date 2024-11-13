package com.RegistrationAndPaymentSystem.service;

import com.RegistrationAndPaymentSystem.dto.PaymentDTO;

public interface PaymentService {
    PaymentDTO initiatePayment(Long userId, String paymentMethod);
    PaymentDTO completePayment(Long paymentId);
}

