package com.youjob.youjob.domain;

import com.youjob.youjob.domain.Enum.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "customers")
public class Customer extends User{

    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL)
    private List<Annonce> annonceList;

}
