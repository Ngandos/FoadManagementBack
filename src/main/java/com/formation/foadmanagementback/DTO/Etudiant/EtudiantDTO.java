package com.formation.foadmanagementback.DTO.Etudiant;

import java.util.UUID;

public record EtudiantDTO(

    UUID uuid,
    String nom,
    String prenom,
    String email,
    String telephone

) {
}
