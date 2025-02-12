package com.youjob.youjob.web.vm.annonce;

import com.youjob.youjob.domain.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResponseHistoryAnnnonceVM {
    private UUID id;
    private String title;
    private String category;
    private Double price;
    private String location;
    private LocalDateTime createdDate;

}
