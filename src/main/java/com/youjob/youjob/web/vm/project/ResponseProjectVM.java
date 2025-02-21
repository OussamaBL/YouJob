package com.youjob.youjob.web.vm.project;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.ProjectProgress;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResponseProjectVM {

    private UUID id;
    private ResponseHistoryAnnnonceVM annonce;
    private LocalDateTime confirmedDate;
    private ProjectProgress progress;
    private Double price;
    private Boolean accepted;
    private LocalDateTime dateComplete;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ResponseHistoryAnnnonceVM getAnnonce() {
        return annonce;
    }

    public void setAnnonce(ResponseHistoryAnnnonceVM annonce) {
        this.annonce = annonce;
    }

    public LocalDateTime getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(LocalDateTime confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public ProjectProgress getProgress() {
        return progress;
    }

    public void setProgress(ProjectProgress progress) {
        this.progress = progress;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public LocalDateTime getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(LocalDateTime dateComplete) {
        this.dateComplete = dateComplete;
    }
}
