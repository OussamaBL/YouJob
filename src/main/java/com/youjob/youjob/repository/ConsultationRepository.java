package com.youjob.youjob.repository;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConsultationRepository extends JpaRepository<Consultation, UUID> {
    Optional<Consultation> findByResponderIdAndAnnonceId(UUID responder_id, UUID annonce_id);
    Optional<Consultation> findByAnnonceIdAndAccepted(UUID annonce_id, Boolean accepted);
    Page<Consultation> findAllByResponderId(UUID responder_id, Pageable pageable);
    Page<Consultation> findAllByAnnonceId(UUID annonce_id, Pageable pageable);
}
