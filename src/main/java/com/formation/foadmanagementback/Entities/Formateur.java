package com.formation.foadmanagementback.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "formateur")
public class Formateur {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(nullable = false)
      private Long id;
      private UUID uuid;
      private String nom;
      private String prenom;
      private String email;
      private String expertise;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @PrePersist
      public void generateUuid() {
            if (uuid == null) {
                uuid = UUID.randomUUID();
            }
      }

}