package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Etudiant.EtudiantCreateDTO;
import com.formation.foadmanagementback.DTO.Etudiant.EtudiantDTO;
import com.formation.foadmanagementback.Entities.Etudiant;

public class EtudiantMapper {

    public static EtudiantDTO toDTO(Etudiant entity) {
        return new EtudiantDTO(
            entity.getUuid(),
            entity.getNom(),
            entity.getPrenom(),
            entity.getEmail(),
            entity.getTelephone()
        );
    }

    public static Etudiant toEntity(EtudiantCreateDTO dto) {
        return Etudiant.builder()
            .nom(dto.nom())
            .prenom(dto.prenom())
            .email(dto.email())
            .telephone(dto.telephone())
            .build(); // UUID sera généré automatiquement
    }

}
