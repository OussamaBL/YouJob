package com.youjob.youjob.repository.impl;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.AnnonceStatus;
import com.youjob.youjob.web.vm.annonce.SearchDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnnonceRepositoryImpl{
    private final EntityManager entityManager;

    public AnnonceRepositoryImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    public Page<Annonce> findByCriteria(SearchDTO searchDTO, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Main query for results
        CriteriaQuery<Annonce> cq = cb.createQuery(Annonce.class);
        Root<Annonce> annonceRoot = cq.from(Annonce.class);

        List<Predicate> predicates = new ArrayList<>();
        // Only include announcements with status ACTIVE
        predicates.add(cb.equal(annonceRoot.get("status"), AnnonceStatus.ACTIVE));

        if (searchDTO.getCategory() != null && !searchDTO.getCategory().isEmpty()) {
            predicates.add(cb.like(
                    cb.lower(annonceRoot.get("category")),
                    "%" + searchDTO.getCategory().toLowerCase() + "%"
            ));
        }

        if (searchDTO.getLocation() != null && !searchDTO.getLocation().isEmpty()) {
            predicates.add(cb.like(
                    cb.lower(annonceRoot.get("location")),
                    "%" + searchDTO.getLocation().toLowerCase() + "%"
            ));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Annonce> query = entityManager.createQuery(cq);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        List<Annonce> annonces = query.getResultList();

        // Count query: create a new root and predicate list
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Annonce> countRoot = countQuery.from(Annonce.class);
        List<Predicate> countPredicates = new ArrayList<>();
        countPredicates.add(cb.equal(countRoot.get("status"), AnnonceStatus.ACTIVE));

        if (searchDTO.getCategory() != null && !searchDTO.getCategory().isEmpty()) {
            countPredicates.add(cb.like(
                    cb.lower(countRoot.get("category")),
                    "%" + searchDTO.getCategory().toLowerCase() + "%"
            ));
        }

        if (searchDTO.getLocation() != null && !searchDTO.getLocation().isEmpty()) {
            countPredicates.add(cb.like(
                    cb.lower(countRoot.get("location")),
                    "%" + searchDTO.getLocation().toLowerCase() + "%"
            ));
        }

        countQuery.select(cb.count(countRoot));
        countQuery.where(countPredicates.toArray(new Predicate[0]));
        Long totalElements = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(annonces, PageRequest.of(page, size), totalElements);
    }

}
