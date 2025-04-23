package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.SupportCours.SupportCoursCreateDTO;
import com.formation.foadmanagementback.DTO.SupportCours.SupportCoursDTO;
import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Entities.Session;
import com.formation.foadmanagementback.Entities.SupportCour;

import java.time.LocalDateTime;

public class SupportCoursMapper {

    public static SupportCoursDTO toDTO(SupportCour entity) {
        return new SupportCoursDTO(
            entity.getUuid(),
            entity.getTitre(),
            entity.getDescription(),
            entity.getFichierUrl(),
            entity.getDatePublication(),
            entity.getSession().getUuid(),
            entity.getFormateur().getUuid()
        );
    }

    public static SupportCour toEntity(SupportCoursCreateDTO dto, Session session, Formateur formateur) {
        return SupportCour.builder()
            .titre(dto.titre())
            .description(dto.description())
            .fichierUrl(dto.fichierUrl())
            .datePublication(LocalDateTime.now())
            .session(session)
            .formateur(formateur)
            .build();
    }

}
