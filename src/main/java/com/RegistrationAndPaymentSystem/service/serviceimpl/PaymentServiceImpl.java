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

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PaymentDTO initiatePayment(Long userId, String paymentMethod) {
        // Initialize a payment and set it to "PENDING"
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus("PENDING");
        payment.setAmount(0.0); // Assume initial amount is set to 0, this might change based on your logic
        payment = paymentRepository.save(payment);

        return convertEntityToDTO(payment);
    }

    @Override
    public PaymentDTO completePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setPaymentStatus("SUCCESS");
        paymentRepository.save(payment);

        // Update user registration status
        User user = payment.getUser();
        user.setRegistrationComplete(true);
        userRepository.save(user);

        return convertEntityToDTO(payment);
    }

    private PaymentDTO convertEntityToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(BigDecimal.valueOf(payment.getAmount()));
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
        return paymentDTO;
    }
}
