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
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(nullable = false, length = 1000)
    private String message;
    @Column(nullable = false)
    private LocalDateTime dateEnvoi;
    @Column(nullable = false)
    private boolean lu;
    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    private Etudiant destinataireEtudiant;
    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur destinataireFormateur;
    @ManyToOne
    @JoinColumn(name = "destinataire_user_id")
    private User destinataireUser;


    @PrePersist
    public void generateUuidAndDate() {
        if (uuid == null) uuid = UUID.randomUUID();
        if (dateEnvoi == null) dateEnvoi = LocalDateTime.now();
    }
}