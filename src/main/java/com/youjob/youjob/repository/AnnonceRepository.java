package com.youjob.youjob.repository;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.AnnonceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, UUID> {
    Page<Annonce> getAllByCreatedByIdAndStatus(UUID id, AnnonceStatus annonceStatus,Pageable pageable);
    Page<Annonce> getAllByStatus(AnnonceStatus annonceStatus,Pageable pageable);
}
