package com.youjob.youjob.domain;

import com.youjob.youjob.domain.Enum.ProjectProgress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "annonce_id")
    private Annonce annonce;

    @Column(name = "confirmed_date", nullable = true)
    private LocalDateTime confirmedDate;

    @Enumerated(EnumType.STRING)
    private ProjectProgress progress;

    private Double price;

    @Column(nullable = true)
    private Boolean accepted;

    @Column(nullable = true)
    private LocalDateTime dateComplete;

    /*@ManyToOne
    @JoinColumn(name = "bricoleur_id")
    private User assignedBricoleur; // Association with User (assigned bricoleur)*/


}
