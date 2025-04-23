package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.Etudiant.EtudiantCreateDTO;
import com.formation.foadmanagementback.DTO.Etudiant.EtudiantDTO;

import java.util.List;
import java.util.UUID;

public interface IEtudiantsServ {

    List<EtudiantDTO> getAllEtudiants();
    EtudiantDTO getEtudiantByUuid(UUID uuid);
    EtudiantDTO createEtudiant(EtudiantCreateDTO dto);

}
