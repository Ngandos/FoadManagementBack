package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Certification.CertificationCreateDTO;
import com.formation.foadmanagementback.DTO.Certification.CertificationDTO;
import com.formation.foadmanagementback.Entities.Certification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICertificationMapper {

    public static CertificationDTO toDTO(Certification entity) {
        return new CertificationDTO(
            entity.getUuid(),
            entity.getNom(),
            entity.getNivRncp()
        );
    }

    public static Certification toEntity(CertificationCreateDTO dto) {
        return Certification.builder()
            .nom(dto.nom())
            .nivRncp(dto.nivRncp())
            .build();
    }

}
