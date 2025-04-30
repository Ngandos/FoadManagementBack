package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Formation.FormationCreateDTO;
import com.formation.foadmanagementback.DTO.Formation.FormationDTO;
import com.formation.foadmanagementback.Entities.Certification;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Entities.Formation;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface IFormationMapper {

    public static FormationDTO toDTO(Formation formation) {
        List<UUID> etudiantsUuids = formation.getEtudiantsList().stream()
            .map(Etudiant::getUuid)
            .toList();

        return new FormationDTO(
            formation.getUuid(),
            formation.getTitre(),
            formation.getDescription(),
            formation.getNiveau(),
            formation.getFormateur() != null ? formation.getFormateur().getUuid() : null,
            formation.getFinalCertif() != null ? formation.getFinalCertif().getUuid() : null,
            etudiantsUuids
        );
    }

    public static Formation toEntity(
        FormationCreateDTO dto,
        Formateur formateur,
        Certification finalCertif,
        Certification prerequisCertif,
        List<Etudiant> etudiants
    ) {
        return Formation.builder()
            .titre(dto.titre())
            .description(dto.description())
            .niveau(dto.niveau())
            .formateur(formateur)
            .finalCertif(finalCertif)
            .prerequisCertif(prerequisCertif)
            .etudiantsList(etudiants)
            .build();
    }

}
