package com.youjob.youjob.web.vm.annonce;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AnnonceUpdateVM {
    @Nullable
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @Nullable
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Nullable
    private String category;

    @Nullable
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @Nullable
    private String location;

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getCategory() {
        return category;
    }

    public void setCategory(@Nullable String category) {
        this.category = category;
    }

    @Nullable
    public Double getPrice() {
        return price;
    }

    public void setPrice(@Nullable Double price) {
        this.price = price;
    }

    @Nullable
    public String getLocation() {
        return location;
    }

    public void setLocation(@Nullable String location) {
        this.location = location;
    }
}
