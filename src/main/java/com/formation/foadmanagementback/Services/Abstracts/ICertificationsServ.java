package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.Certification.CertificationCreateDTO;
import com.formation.foadmanagementback.DTO.Certification.CertificationDTO;

import java.util.List;
import java.util.UUID;

public interface ICertificationsServ {

    List<CertificationDTO> getAll();
    CertificationDTO getByUuid(UUID uuid);
    CertificationDTO create(CertificationCreateDTO dto);

}
