package com.RegistrationAndPaymentSystem.service;

import com.RegistrationAndPaymentSystem.dto.PaymentDTO;
import java.util.List;

public interface PaymentService {

    PaymentDTO initiatePayment(Long userId, String paymentMethod, Double amount);

    PaymentDTO completePayment(Long paymentId);

    List<PaymentDTO> getPaymentsByUserId(Long userId);
}
