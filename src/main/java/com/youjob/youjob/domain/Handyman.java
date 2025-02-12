package com.youjob.youjob.domain;

import com.youjob.youjob.domain.Enum.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "handymans")
public class Handyman extends User{

    @Column(nullable = true)
    private String skills;

    @Column(nullable = true)
    private Integer rating;

    @OneToMany(mappedBy = "responder", cascade = CascadeType.ALL)
    private List<Consultation> consultations; // Responses to ads and proposed services

}
