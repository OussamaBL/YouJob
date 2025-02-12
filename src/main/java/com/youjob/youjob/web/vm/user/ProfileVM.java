package com.youjob.youjob.web.vm.user;

import com.youjob.youjob.domain.Enum.UserRole;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

public class ProfileVM {
    @Nullable
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    private String username;

    @Nullable
    @Size(min = 3, message = "Name must be between 3 and 20 characters long")
    private String name;

    @Nullable
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number should be valid and contain 10 to 15 digits"
    )
    private String phoneNumber;

    @Nullable
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
    private Integer rating; // & Handyman

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Nullable String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Nullable
    public Double getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(@Nullable Double vatNumber) {
        this.vatNumber = vatNumber;
    }

    @Nullable
    public String getSkills() {
        return skills;
    }

    public void setSkills(@Nullable String skills) {
        this.skills = skills;
    }

    @Nullable
    public Integer getRating() {
        return rating;
    }

    public void setRating(@Nullable Integer rating) {
        this.rating = rating;
    }
}
