package com.formation.foadmanagementback.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "support_cours")
public class SupportCour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Column(nullable = false)
    private String titre;

    @Column(length = 1000)
    private String description;

    private String fichierUrl; // Chemin ou nom du fichier uploadé

    @Column(nullable = false)
    private LocalDateTime datePublication;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @PrePersist
    public void prePersist() {
        if (uuid == null) uuid = UUID.randomUUID();
        if (datePublication == null) datePublication = LocalDateTime.now();
    }

}