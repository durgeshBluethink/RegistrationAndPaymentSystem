package com.RegistrationAndPaymentSystem.service.serviceimpl;

import com.RegistrationAndPaymentSystem.dto.PaymentDTO;
import com.RegistrationAndPaymentSystem.entity.Payment;
import com.RegistrationAndPaymentSystem.entity.User;
import com.RegistrationAndPaymentSystem.exception.ResourceNotFoundException;
import com.RegistrationAndPaymentSystem.repository.PaymentRepository;
import com.RegistrationAndPaymentSystem.repository.UserRepository;
import com.RegistrationAndPaymentSystem.service.PaymentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PaymentDTO initiatePayment(Long userId, String paymentMethod, Double amount) {
        // Find the user by userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Create and save the payment
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(amount);
        payment.setStatus("PENDING");
        payment.setPaymentDate(LocalDateTime.now());

        payment = paymentRepository.save(payment);

        return convertEntityToDTO(payment);
    }

    @Override
    public PaymentDTO completePayment(Long paymentId) {
        // Find the payment by ID
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        // Complete the payment
        payment.setStatus("SUCCESS");
        payment = paymentRepository.save(payment);

        return convertEntityToDTO(payment);
    }

    @Override
    public List<PaymentDTO> getPaymentsByUserId(Long userId) {
        // Find payments by userId
        List<Payment> payments = paymentRepository.findByUser_UserId(userId);

        return payments.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private PaymentDTO convertEntityToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setUserId(payment.getUser().getUserId());
        paymentDTO.setAmount(BigDecimal.valueOf(payment.getAmount()));
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        paymentDTO.setStatus(payment.getStatus());
        return paymentDTO;
    }
}
