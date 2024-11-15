package com.RegistrationAndPaymentSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {

    private Long userId;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
    private String mobileNumber;

    private String fatherName;
    private String city;
    private String qualification;

    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid pin number")
    private String pinNumber;

    @Email(message = "Invalid email address")
    private String email;

    private String password;
}
