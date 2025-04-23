package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.Formation.FormationCreateDTO;
import com.formation.foadmanagementback.DTO.Formation.FormationDTO;

import java.util.List;
import java.util.UUID;

public interface IFormationsServ {

    List<FormationDTO> getAllFormations();
    FormationDTO getFormationByUuid(UUID uuid);
    FormationDTO createFormation(FormationCreateDTO dto);

}
