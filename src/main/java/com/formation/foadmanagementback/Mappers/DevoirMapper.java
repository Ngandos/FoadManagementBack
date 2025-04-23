package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Devoirs.DevoirCreateDTO;
import com.formation.foadmanagementback.DTO.Devoirs.DevoirDTO;
import com.formation.foadmanagementback.Entities.Devoir;
import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Entities.Session;

public class DevoirMapper {

    public static DevoirDTO toDTO(Devoir entity) {
        return new DevoirDTO(
            entity.getUuid(),
            entity.getTitre(),
            entity.getConsigne(),
            entity.getFichierUrl(),
            entity.getDateLimite(),
            entity.getSession().getUuid(),
            entity.getFormateur().getUuid()
        );
    }

    public static Devoir toEntity(DevoirCreateDTO dto, Session session, Formateur formateur) {
        return Devoir.builder()
            .titre(dto.titre())
            .consigne(dto.consigne())
            .fichierUrl(dto.fichierUrl())
            .dateLimite(dto.dateLimite())
            .session(session)
            .formateur(formateur)
            .build();
    }

}
