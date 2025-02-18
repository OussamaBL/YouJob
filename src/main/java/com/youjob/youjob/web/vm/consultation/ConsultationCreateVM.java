package com.youjob.youjob.web.vm.consultation;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConsultationCreateVM {
    @NotNull(message = "annonce ID is required")
    private UUID annonce_id;
    @NotNull(message = "user_id ID is required")
    private UUID user_id;
    private LocalDateTime responseDate=LocalDateTime.now();
    @NotNull(message = "message is required")
    private String message;
    private Boolean accepted= null;

    public UUID getAnnonce_id() {
        return annonce_id;
    }

    public void setAnnonce_id(UUID annonce_id) {
        this.annonce_id = annonce_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDateTime responseDate) {
        this.responseDate = responseDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
