package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.SupportCours.SupportCoursCreateDTO;
import com.formation.foadmanagementback.DTO.SupportCours.SupportCoursDTO;

import java.util.List;
import java.util.UUID;

public interface ISupportsCoursServ {

    SupportCoursDTO create(SupportCoursCreateDTO dto);
    List<SupportCoursDTO> getAll();
    SupportCoursDTO getByUuid(UUID uuid);
    List<SupportCoursDTO> getBySession(UUID sessionUuid);
    List<SupportCoursDTO> getByFormateur(UUID formateurUuid);

}
