package com.RegistrationAndPaymentSystem.service.serviceimpl;

import com.RegistrationAndPaymentSystem.dto.UserDTO;
import com.RegistrationAndPaymentSystem.entity.User;
import com.RegistrationAndPaymentSystem.exception.ResourceNotFoundException;
import com.RegistrationAndPaymentSystem.exception.UserAlreadyExistsException;
import com.RegistrationAndPaymentSystem.repository.UserRepository;
import com.RegistrationAndPaymentSystem.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserDTO userDTO) {
        // Check if a user with the same email already exists
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + userDTO.getEmail() + " already exists.");
        }

        // Convert UserDTO to User entity
        User user = convertDTOToEntity(userDTO);

        // Set default role to USER if not provided in DTO
        if (user.getRole() == null) {
            user.setRole(User.Role.USER); // Setting default role to USER
        }

        // Encrypt the user's password
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRegistrationComplete(true);

        // Save the new user to the database
        return userRepository.save(user);
    }

    @Override
    public UserDTO referUser(Long referrerId, UserDTO referredUserDTO) {
        // Find the referrer by ID
        User referrer = userRepository.findById(referrerId)
                .orElseThrow(() -> new ResourceNotFoundException("Referrer not found with ID: " + referrerId));

        // Convert referredUserDTO to User entity
        User referredUser = convertDTOToEntity(referredUserDTO);
        referredUser.setReferredBy(referrer); // Set the referrer relationship

        // Set default role for referred users
        referredUser.setRole(User.Role.USER);

        // Encrypt the password for the referred user
        referredUser.setPassword(passwordEncoder.encode(referredUserDTO.getPassword()));
        referredUser.setRegistrationComplete(true);

        // Save the referred user to the database
        userRepository.save(referredUser);

        return convertEntityToDTO(referredUser);
    }

    @Override
    public List<UserDTO> getReferralStatus(Long userId) {
        // Find the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        // Get all referrals made by the user
        return user.getReferrals().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    // Utility method to convert UserDTO to User entity
    private User convertDTOToEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setFatherName(userDTO.getFatherName());
        user.setCity(userDTO.getCity());
        user.setQualification(userDTO.getQualification());
        user.setPinNumber(userDTO.getPinNumber());
        user.setEmail(userDTO.getEmail());

        // Convert String role to Enum role if provided in DTO
        user.setRole(userDTO.getRole() != null ? userDTO.getRole() : User.Role.USER);
        return user;
    }

    // Utility method to convert User entity to UserDTO
    private UserDTO convertEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setMobileNumber(user.getMobileNumber());
        userDTO.setFatherName(user.getFatherName());
        userDTO.setCity(user.getCity());
        userDTO.setQualification(user.getQualification());
        userDTO.setPinNumber(user.getPinNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
