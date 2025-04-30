package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Etudiant.EtudiantCreateDTO;
import com.formation.foadmanagementback.DTO.Etudiant.EtudiantDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Mappers.IEtudiantMapper;
import com.formation.foadmanagementback.Repositories.IEtudiantsRepository;
import com.formation.foadmanagementback.Services.Abstracts.IEtudiantsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EtudiantsServImplements implements IEtudiantsServ {

    private final IEtudiantsRepository iEtudiantsRepository;
    private final IEtudiantMapper iEtudiantMapper;


    @Override
    public List<EtudiantDTO> getAllEtudiants() {
        return iEtudiantsRepository.findAll().stream()
            .map(iEtudiantMapper::toDTO)
            .toList();
    }

    @Override
    public EtudiantDTO getEtudiantByUuid(UUID uuid) {
        Etudiant etudiant = iEtudiantsRepository.findByUuid(uuid)
        .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        return iEtudiantMapper.toDTO(etudiant);
    }

    @Override
    public EtudiantDTO createEtudiant(EtudiantCreateDTO dto) {
        Etudiant etudiant = iEtudiantMapper.toEntity(dto);
        Etudiant saved = iEtudiantsRepository.save(etudiant);
        return iEtudiantMapper.toDTO(saved);
    }
}
