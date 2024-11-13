package com.RegistrationAndPaymentSystem.service;

import com.RegistrationAndPaymentSystem.dto.UserDTO;
import com.RegistrationAndPaymentSystem.entity.User;
import java.util.List;

public interface UserService {
    /**
     * Registers a new user.
     *
     * @param userDTO The user data for registration.
     * @return The newly registered User entity.
     */
    User registerUser(UserDTO userDTO);

    /**
     * Refers a new user using an existing user's ID.
     *
     * @param referrerId The ID of the referrer.
     * @param referredUserDTO The user data for the referred user.
     * @return The UserDTO of the referred user.
     */
    UserDTO referUser(Long referrerId, UserDTO referredUserDTO);

    /**
     * Gets all referrals made by a specific user.
     *
     * @param userId The ID of the user whose referrals are to be fetched.
     * @return List of UserDTOs representing the referred users.
     */
    List<UserDTO> getReferralStatus(Long userId);
}
