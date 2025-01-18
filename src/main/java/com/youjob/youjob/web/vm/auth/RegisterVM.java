package com.youjob.youjob.web.vm.auth;

import com.youjob.youjob.domain.Enum.UserRole;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVM {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, and one number"
    )
    private String password;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number should be valid and contain 10 to 15 digits"
    )
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(
            min = 5,
            max = 255,
            message = "Address should be between 5 and 255 characters"
    )
    private String address;

    @NotNull(message = "Role is required")
    private UserRole role;
    @Nullable
    private Double vatNumber; // For Business
    @Nullable
    private String skills; // For Handyman
    @Nullable
    private Integer rating; // For Handyman
}
