package com.formation.foadmanagementback.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "formations")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    private String titre;
    private String description;
    private String niveau;
    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;
    @ManyToOne
    @JoinColumn(name = "certification_id")
    private Certification finalCertif;
    @ManyToOne
    @JoinColumn(name = "prerequis_certification_id")
    private Certification prerequisCertif;
    @ManyToMany
    @JoinTable(
        name = "formation_etudiants",
        joinColumns = @JoinColumn(name = "formation_id"),
        inverseJoinColumns = @JoinColumn(name = "etudiant_id")
    )
    private List<Etudiant> etudiantsList = new ArrayList<>();

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

}