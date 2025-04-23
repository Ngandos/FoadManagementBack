package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.Devoirs.DevoirCreateDTO;
import com.formation.foadmanagementback.DTO.Devoirs.DevoirDTO;

import java.util.List;
import java.util.UUID;

public interface IDevoirsServ {

    DevoirDTO create(DevoirCreateDTO dto);
    List<DevoirDTO> getAll();
    DevoirDTO getByUuid(UUID uuid);
    List<DevoirDTO> getBySession(UUID sessionUuid);
    List<DevoirDTO> getByFormateur(UUID formateurUuid);

}
