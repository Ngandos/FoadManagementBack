package com.formation.foadmanagementback.DTO.Etudiant;

public record EtudiantCreateDTO(

    String nom,
    String prenom,
    String email,
    String telephone

) {
}
