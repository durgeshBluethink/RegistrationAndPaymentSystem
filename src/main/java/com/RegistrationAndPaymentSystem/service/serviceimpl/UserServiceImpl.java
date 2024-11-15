package com.RegistrationAndPaymentSystem.service.serviceimpl;

import com.RegistrationAndPaymentSystem.dto.UserDTO;
import com.RegistrationAndPaymentSystem.entity.User;
import com.RegistrationAndPaymentSystem.exception.ResourceNotFoundException;
import com.RegistrationAndPaymentSystem.repository.UserRepository;
import com.RegistrationAndPaymentSystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setRegistrationComplete(false);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getReferrals(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return userRepository.findByReferredBy(user);
    }

    @Override
    public void completeRegistration(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setRegistrationComplete(true);
            userRepository.save(user);
        }
    }

    @Override
    public UserDTO referUser(Long referrerId, UserDTO referredUserDTO) {
        Optional<User> referrerOpt = userRepository.findById(referrerId);
        if (referrerOpt.isPresent()) {
            User referredUser = modelMapper.map(referredUserDTO, User.class);
            referredUser.setReferredBy(referrerOpt.get());
            referredUser.setRegistrationComplete(false);
            User savedUser = userRepository.save(referredUser);
            return modelMapper.map(savedUser, UserDTO.class);
        } else {
            throw new ResourceNotFoundException("Referrer with ID " + referrerId + " not found.");
        }
    }

    @Override
    public List<UserDTO> getReferralStatus(Long userId) {
        List<User> referrals = getReferrals(userId);
        return referrals.stream()
                .map(referral -> modelMapper.map(referral, UserDTO.class))
                .collect(Collectors.toList());
    }
}
