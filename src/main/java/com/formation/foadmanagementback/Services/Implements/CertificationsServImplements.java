package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Certification.*;
import com.formation.foadmanagementback.Entities.Certification;
import com.formation.foadmanagementback.Mappers.ICertificationMapper;
import com.formation.foadmanagementback.Repositories.ICertificationsRepository;
import com.formation.foadmanagementback.Services.Abstracts.ICertificationsServ;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
public class CertificationsServImplements implements ICertificationsServ {

    private final ICertificationsRepository iCertificationsRepository;

    public CertificationsServImplements(ICertificationsRepository iCertificationsRepository) {
        this.iCertificationsRepository = iCertificationsRepository;
    }

    @Override
    public List<CertificationDTO> getAll() {
        return iCertificationsRepository.findAll().stream()
            .map(ICertificationMapper::toDTO)
            .toList();
    }

    @Override
    public CertificationDTO getByUuid(UUID uuid) {
        return iCertificationsRepository.findByUuid(uuid)
            .map(ICertificationMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Certification non trouvée"));
    }

    @Override
    public CertificationDTO create(CertificationCreateDTO dto) {
        Certification certification = ICertificationMapper.toEntity(dto);
        Certification saved = iCertificationsRepository.save(certification);
        return ICertificationMapper.toDTO(saved);
    }
}
