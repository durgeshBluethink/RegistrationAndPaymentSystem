package com.RegistrationAndPaymentSystem.controller;

import com.RegistrationAndPaymentSystem.dto.UserDTO;
import com.RegistrationAndPaymentSystem.entity.User;
import com.RegistrationAndPaymentSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserDTO userDTO) {
        User registeredUser = userService.registerUser(userDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // Refer a new user
    @PostMapping("/{userId}/refer")
    public ResponseEntity<UserDTO> referUser(@PathVariable Long userId, @RequestBody @Valid UserDTO referredUserDTO) {
        UserDTO referredUser = userService.referUser(userId, referredUserDTO);
        return new ResponseEntity<>(referredUser, HttpStatus.CREATED);
    }

    // Get referral status
    @GetMapping("/{userId}/referrals")
    public ResponseEntity<List<UserDTO>> getReferralStatus(@PathVariable Long userId) {
        List<UserDTO> referrals = userService.getReferralStatus(userId);
        return ResponseEntity.ok(referrals);
    }
}
