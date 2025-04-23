package com.formation.foadmanagementback.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "remises_devoirs")
public class RemiseDevoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "devoir_id")
    private Devoir devoir;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @Column(nullable = false)
    private LocalDateTime dateRemise;

    private String fichierUrl;

    private String commentaire;

    @PrePersist
    public void generateUuidAndDate() {
        if (uuid == null) uuid = UUID.randomUUID();
        if (dateRemise == null) dateRemise = LocalDateTime.now();
    }

}
