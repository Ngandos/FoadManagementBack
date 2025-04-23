package com.formation.foadmanagementback.DTO.Certification;

import com.formation.foadmanagementback.Enums.NiveauRncp;

import java.util.UUID;

public record CertificationDTO(

    UUID uuid,
    String nom,
    NiveauRncp niveauRncp

) {
}
