package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Formation.FormationCreateDTO;
import com.formation.foadmanagementback.DTO.Formation.FormationDTO;
import com.formation.foadmanagementback.Entities.Certification;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Entities.Formation;
import com.formation.foadmanagementback.Mappers.FormationMapper;
import com.formation.foadmanagementback.Repositories.ICertificationsRepository;
import com.formation.foadmanagementback.Repositories.IEtudiantsRepository;
import com.formation.foadmanagementback.Repositories.IFormateursRepository;
import com.formation.foadmanagementback.Repositories.IFormationsRepository;
import com.formation.foadmanagementback.Services.Abstracts.IFormationsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormationsServImplements implements IFormationsServ {

    private final IFormationsRepository iFormationsRepository;
    private final IFormateursRepository iFormateursRepository;
    private final ICertificationsRepository iCertificationsRepository;
    private final IEtudiantsRepository iEtudiantsRepository;

    @Override
    public List<FormationDTO> getAllFormations() {
        return iFormationsRepository.findAll().stream()
            .map(FormationMapper::toDTO)
            .toList();
    }

    @Override
    public FormationDTO getFormationByUuid(UUID uuid) {
        return iFormationsRepository.findByUuid(uuid)
            .map(FormationMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
    }

    @Override
    public FormationDTO createFormation(FormationCreateDTO dto) {
        Formateur formateur = iFormateursRepository.findByUuid(dto.formateurUuid())
            .orElseThrow(() -> new RuntimeException("Formateur non trouvé"));

        Certification finalCertif = iCertificationsRepository.findByUuid(dto.finalCertifUuid())
            .orElseThrow(() -> new RuntimeException("Certification finale non trouvée"));

        Certification prerequisCertif = iCertificationsRepository.findByUuid(dto.prerequisCertifUuid())
            .orElseThrow(() -> new RuntimeException("Certification prérequis non trouvée"));

        List<Etudiant> etudiants = dto.etudiantsUuids().stream()
            .map(uuid -> iEtudiantsRepository.findByUuid(uuid)
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé pour uuid : " + uuid)))
            .toList();

        Formation formation = FormationMapper.toEntity(dto, formateur, finalCertif, prerequisCertif, etudiants);
        Formation saved = iFormationsRepository.save(formation);
        return FormationMapper.toDTO(saved);
    }
}
