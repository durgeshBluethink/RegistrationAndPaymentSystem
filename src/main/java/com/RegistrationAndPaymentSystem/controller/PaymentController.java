package com.RegistrationAndPaymentSystem.controller;

import com.RegistrationAndPaymentSystem.dto.PaymentDTO;
import com.RegistrationAndPaymentSystem.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{userId}/initiate")
    public ResponseEntity<PaymentDTO> initiatePayment(@PathVariable Long userId, @RequestParam String paymentMethod) {
        return ResponseEntity.ok(paymentService.initiatePayment(userId, paymentMethod));
    }

    @PostMapping("/complete/{paymentId}")
    public ResponseEntity<PaymentDTO> completePayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.completePayment(paymentId));
    }
}

