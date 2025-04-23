package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.RemiseDevoirDTO.RemiseDevoirCreateDTO;
import com.formation.foadmanagementback.DTO.RemiseDevoirDTO.RemiseDevoirDTO;

import java.util.List;
import java.util.UUID;

public interface IRemisesDevoirsServ {

    RemiseDevoirDTO create(RemiseDevoirCreateDTO dto);
    RemiseDevoirDTO getByUuid(UUID uuid);
    List<RemiseDevoirDTO> getByEtudiant(UUID etudiantUuid);
    List<RemiseDevoirDTO> getByDevoir(UUID devoirUuid);

}
