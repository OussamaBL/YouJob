package com.youjob.youjob.web.vm.consultation;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.User;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.user.ResponseUserVM;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResponseConsultationVM {
    private ResponseHistoryAnnnonceVM annonce;
    private ResponseUserVM responder;
    private LocalDateTime responseDate;
    private String message;
    private Boolean accepted;

    public ResponseHistoryAnnnonceVM getAnnonce() {
        return annonce;
    }

    public void setAnnonce(ResponseHistoryAnnnonceVM annonce) {
        this.annonce = annonce;
    }

    public ResponseUserVM getResponder() {
        return responder;
    }

    public void setResponder(ResponseUserVM responder) {
        this.responder = responder;
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

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
