package com.formation.foadmanagementback.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "devoirs")
public class Devoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    private String titre;

    @Column(nullable = false, length = 1000)
    private String consigne;

    private String fichierUrl;

    private LocalDate dateLimite;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) uuid = UUID.randomUUID();
    }

}