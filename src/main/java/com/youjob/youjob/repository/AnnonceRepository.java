package com.youjob.youjob.repository;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.AnnonceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, UUID> {
    Page<Annonce> getAllByCreatedByIdAndStatus(UUID id, AnnonceStatus annonceStatus,Pageable pageable);
    Page<Annonce> getAllByStatus(AnnonceStatus annonceStatus,Pageable pageable);

 /*   @Query("SELECT a FROM Annonce a WHERE a.status = :status AND (LOWER(a.category) LIKE LOWER(CONCAT('%', :category, '%')) OR LOWER(a.location) LIKE LOWER(CONCAT('%', :location, '%')))")
    Page<Annonce> filterAnnonceByCategoryAndLocation(@Param("status") AnnonceStatus status, @Param("category") String category, @Param("location") String location, Pageable pageable);
*/
}
