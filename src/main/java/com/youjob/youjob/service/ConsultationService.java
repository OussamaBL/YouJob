package com.youjob.youjob.service;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Consultation;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ConsultationService {
    Consultation CreateConsultation(Consultation consultation);
    Consultation approveConsultation(UUID uuid);
    Consultation rejectConsultation(UUID uuid);
    void deleteConsultation(UUID uuid);
    Page<Consultation> getAnnonceConsultationUser(UUID uuid,int page, int size);
    Page<Consultation> getResponderAnnonce(UUID uuid,int page, int size);


}
