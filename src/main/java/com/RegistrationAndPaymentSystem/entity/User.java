package com.RegistrationAndPaymentSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
    private String mobileNumber;

    @NotBlank(message = "Father's name is required")
    private String fatherName;

    @NotBlank(message = "City is required")
    private String city;

    private String qualification;

    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid pin number")
    private String pinNumber;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // Using Enum for role

    private Boolean registrationComplete = false;

    @OneToMany(mappedBy = "referredBy")
    private List<User> referrals;

    @ManyToOne
    @JoinColumn(name = "referred_by")
    private User referredBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }

    public enum Role {
        USER,
        ADMIN
    }
}
