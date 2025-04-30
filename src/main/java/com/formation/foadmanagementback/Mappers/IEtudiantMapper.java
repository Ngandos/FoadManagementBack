package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Etudiant.EtudiantCreateDTO;
import com.formation.foadmanagementback.DTO.Etudiant.EtudiantDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEtudiantMapper {

    EtudiantDTO toDTO(Etudiant entity);

    Etudiant toEntity(EtudiantCreateDTO dto);
}
