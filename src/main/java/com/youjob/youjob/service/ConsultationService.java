package com.youjob.youjob.service;

import com.youjob.youjob.domain.Consultation;

import java.util.UUID;

public interface ConsultationService {
    Consultation CreateConsultation(Consultation consultation);
    Consultation approveConsultation(UUID uuid);
    Consultation rejectConsultation(UUID uuid);
}
