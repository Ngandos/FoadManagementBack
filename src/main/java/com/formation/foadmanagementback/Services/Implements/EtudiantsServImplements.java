package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Etudiant.EtudiantCreateDTO;
import com.formation.foadmanagementback.DTO.Etudiant.EtudiantDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Mappers.EtudiantMapper;
import com.formation.foadmanagementback.Repositories.IEtudiantsRepository;
import com.formation.foadmanagementback.Services.Abstracts.IEtudiantsServ;

import java.util.List;
import java.util.UUID;

public class EtudiantsServImplements implements IEtudiantsServ {

    private final IEtudiantsRepository iEtudiantsRepository;

    public EtudiantsServImplements(IEtudiantsRepository iEtudiantsRepository) {
        this.iEtudiantsRepository = iEtudiantsRepository;
    }

    @Override
    public List<EtudiantDTO> getAllEtudiants() {
        return iEtudiantsRepository.findAll().stream()
            .map(EtudiantMapper::toDTO)
            .toList();
    }

    @Override
    public EtudiantDTO getEtudiantByUuid(UUID uuid) {
        Etudiant etudiant = iEtudiantsRepository.findByUuid(uuid)
        .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        return EtudiantMapper.toDTO(etudiant);
    }

    @Override
    public EtudiantDTO createEtudiant(EtudiantCreateDTO dto) {
        Etudiant etudiant = EtudiantMapper.toEntity(dto);
        Etudiant saved = iEtudiantsRepository.save(etudiant);
        return EtudiantMapper.toDTO(saved);
    }
}
