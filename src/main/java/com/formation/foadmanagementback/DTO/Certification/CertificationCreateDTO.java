package com.formation.foadmanagementback.DTO.Certification;

import com.formation.foadmanagementback.Enums.NiveauRncp;

public record CertificationCreateDTO(

    String nom,
    NiveauRncp niveauRncp

) {
}
