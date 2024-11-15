package com.RegistrationAndPaymentSystem.controller;

import com.RegistrationAndPaymentSystem.dto.PaymentDTO;
import com.RegistrationAndPaymentSystem.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Endpoint to initiate a payment
    @PostMapping("/{userId}/initiate")
    public ResponseEntity<PaymentDTO> initiatePayment(
            @PathVariable Long userId,
            @RequestParam String paymentMethod,
            @RequestParam Double amount) {
        PaymentDTO paymentDTO = paymentService.initiatePayment(userId, paymentMethod, amount);
        return ResponseEntity.ok(paymentDTO);
    }

    // Endpoint to complete a payment
    @PostMapping("/complete/{paymentId}")
    public ResponseEntity<PaymentDTO> completePayment(@PathVariable Long paymentId) {
        PaymentDTO paymentDTO = paymentService.completePayment(paymentId);
        return ResponseEntity.ok(paymentDTO);
    }

    // Endpoint to get payments by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserId(@PathVariable Long userId) {
        List<PaymentDTO> paymentDTOs = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(paymentDTOs);
    }
}
