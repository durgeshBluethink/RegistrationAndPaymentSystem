package com.RegistrationAndPaymentSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Double amount;
    private String paymentMethod;  // e.g., UPI, Debit Card, etc.
    private LocalDateTime paymentDate;
    private String status;  // e.g., SUCCESS, FAILED, PENDING
}
