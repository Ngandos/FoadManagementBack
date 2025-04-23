package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.RemiseDevoirDTO.RemiseDevoirCreateDTO;
import com.formation.foadmanagementback.DTO.RemiseDevoirDTO.RemiseDevoirDTO;
import com.formation.foadmanagementback.Entities.Devoir;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.RemiseDevoir;

import java.time.LocalDateTime;

public class RemiseDevoirMapper {

    public static RemiseDevoirDTO toDTO(RemiseDevoir entity) {
        return new RemiseDevoirDTO(
                entity.getUuid(),
                entity.getDevoir().getUuid(),
                entity.getEtudiant().getUuid(),
                entity.getDateRemise(),
                entity.getFichierUrl(),
                entity.getCommentaire()
        );
    }

    public static RemiseDevoir toEntity(RemiseDevoirCreateDTO dto, Devoir devoir, Etudiant etudiant) {
        return RemiseDevoir.builder()
                .devoir(devoir)
                .etudiant(etudiant)
                .fichierUrl(dto.fichierUrl())
                .commentaire(dto.commentaire())
                .dateRemise(LocalDateTime.now())
                .build();
    }

}
