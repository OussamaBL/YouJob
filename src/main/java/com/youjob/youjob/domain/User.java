package com.youjob.youjob.domain;

import com.youjob.youjob.domain.Enum.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}