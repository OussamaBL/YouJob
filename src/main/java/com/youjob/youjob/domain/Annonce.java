package com.youjob.youjob.domain;

import com.youjob.youjob.domain.Enum.AnnonceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "annonces")
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private String category;

    private Double price;

    private String location;

    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private AnnonceStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy; /*business or customer*/

    @OneToOne(mappedBy = "annonce", cascade = CascadeType.ALL)
    private Project project;

    @OneToMany(mappedBy = "annonce", cascade = CascadeType.ALL)
    private List<Consultation> consultationList;

}
