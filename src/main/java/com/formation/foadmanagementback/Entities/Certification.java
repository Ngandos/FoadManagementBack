package com.formation.foadmanagementback.Entities;

import com.formation.foadmanagementback.Enums.NiveauRncp;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    private String nom;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NiveauRncp nivRncp;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }


}