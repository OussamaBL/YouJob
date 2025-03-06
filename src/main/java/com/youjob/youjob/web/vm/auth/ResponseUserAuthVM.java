package com.youjob.youjob.web.vm.auth;

import com.youjob.youjob.domain.Enum.UserRole;

import java.util.UUID;

public class ResponseUserAuthVM {
    private UUID id;
    private String username;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private UserRole role;
    private Integer rating;
    private String skills;
    private Double vatNumber;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Double getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(Double vatNumber) {
        this.vatNumber = vatNumber;
    }
}
