package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Formateur.FormateurCreateDTO;
import com.formation.foadmanagementback.DTO.Formateur.FormateurDTO;
import com.formation.foadmanagementback.Entities.Formateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IFormateurMapper {

    public static FormateurDTO toDTO(Formateur entity) {

        return new FormateurDTO(
            entity.getUuid(),
            entity.getNom(),
            entity.getPrenom(),
            entity.getEmail(),
            entity.getExpertise()
        );
    }

    public static Formateur toEntity(FormateurCreateDTO dto) {
        return Formateur.builder()
            .nom(dto.nom())
            .prenom(dto.prenom())
            .email(dto.email())
            .expertise(dto.expertise())
            .build();
    }

}
