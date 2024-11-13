package com.RegistrationAndPaymentSystem.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import com.RegistrationAndPaymentSystem.entity.User.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
    private String mobileNumber;

    @NotBlank(message = "Father's name is required")
    private String fatherName;

    @NotBlank(message = "City is required")
    private String city;

    private String qualification;

    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid pin number")
    private String pinNumber;

    @NotBlank(message = "Password is required")
    private String password;

    private Role role; // Using Enum type for role
}
