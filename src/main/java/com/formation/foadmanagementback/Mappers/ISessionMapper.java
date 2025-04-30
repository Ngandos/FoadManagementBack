package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Session.SessionCreateDTO;
import com.formation.foadmanagementback.DTO.Session.SessionDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.Formation;
import com.formation.foadmanagementback.Entities.Session;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ISessionMapper {

    public static SessionDTO toDTO(Session entity) {
        List<UUID> etudiantsUuids = entity.getEtudiants().stream()
            .map(Etudiant::getUuid)
            .toList();

        return new SessionDTO(
            entity.getUuid(),
            entity.getNom(),
            entity.getDateDebut(),
            entity.getDateFin(),
            entity.getFormation().getUuid(),
            etudiantsUuids
        );
    }

    public static Session toEntity(SessionCreateDTO dto, Formation formation, List<Etudiant> etudiants) {
        return Session.builder()
            .nom(dto.nom())
            .dateDebut(dto.dateDebut())
            .dateFin(dto.dateFin())
            .formation(formation)
            .etudiants(etudiants)
            .build();
    }

}
