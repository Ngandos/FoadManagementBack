package com.formation.foadmanagementback.DTO.Formateur;

import java.util.UUID;

public record FormateurDTO(

     UUID uuid,
     String nom,
     String prenom,
     String email,
     String expertise

) {
}
