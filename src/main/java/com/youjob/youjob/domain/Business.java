package com.youjob.youjob.domain;

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
@Table(name = "businesses")
public class Business extends User {

    @Column(nullable = true)
    private Double vatNumber;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Annonce> annonceList;
}