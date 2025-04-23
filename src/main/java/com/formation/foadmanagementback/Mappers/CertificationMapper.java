package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Certification.CertificationCreateDTO;
import com.formation.foadmanagementback.DTO.Certification.CertificationDTO;
import com.formation.foadmanagementback.Entities.Certification;

public class CertificationMapper {

    public static CertificationDTO toDTO(Certification entity) {
        return new CertificationDTO(
            entity.getUuid(),
            entity.getNom(),
            entity.getNiveauRncp()
        );
    }

    public static Certification toEntity(CertificationCreateDTO dto) {
        return Certification.builder()
            .nom(dto.nom())
            .niveauRncp(dto.niveauRncp())
            .build();
    }

}
