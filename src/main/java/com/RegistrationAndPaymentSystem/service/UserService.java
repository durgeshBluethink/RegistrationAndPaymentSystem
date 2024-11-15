package com.RegistrationAndPaymentSystem.service;

import com.RegistrationAndPaymentSystem.dto.UserDTO;
import com.RegistrationAndPaymentSystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerUser(UserDTO userDTO);

    Optional<User> findByEmail(String email);

    List<User> getReferrals(Long userId);

    void completeRegistration(Long userId);

    UserDTO referUser(Long referrerId, UserDTO referredUserDTO);

    List<UserDTO> getReferralStatus(Long userId);
}
