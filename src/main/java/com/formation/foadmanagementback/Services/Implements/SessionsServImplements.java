package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Session.SessionCreateDTO;
import com.formation.foadmanagementback.DTO.Session.SessionDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.Formation;
import com.formation.foadmanagementback.Entities.Session;
import com.formation.foadmanagementback.Mappers.ISessionMapper;
import com.formation.foadmanagementback.Repositories.IEtudiantsRepository;
import com.formation.foadmanagementback.Repositories.IFormationsRepository;
import com.formation.foadmanagementback.Repositories.ISessionsRepository;
import com.formation.foadmanagementback.Services.Abstracts.ISessionsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionsServImplements implements ISessionsServ {

    private final ISessionsRepository iSessionsRepository;
    private final IFormationsRepository iFormationsRepository;
    private final IEtudiantsRepository iEtudiantsRepository;

    @Override
    public List<SessionDTO> getAllSessions() {
        return iSessionsRepository.findAll().stream()
            .map(ISessionMapper::toDTO)
            .toList();
    }

    @Override
    public SessionDTO getSessionByUuid(UUID uuid) {
        return iSessionsRepository.findByUuid(uuid)
            .map(ISessionMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Session non trouvée"));
    }

    @Override
    public SessionDTO createSession(SessionCreateDTO dto) {
        Formation formation = iFormationsRepository.findByUuid(dto.formationUuid())
            .orElseThrow(() -> new RuntimeException("Formation non trouvée"));

        List<Etudiant> etudiants = dto.etudiantsUuids().stream()
            .map(uuid -> iEtudiantsRepository.findByUuid(uuid)
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé pour uuid : " + uuid)))
            .toList();

        Session session = ISessionMapper.toEntity(dto, formation, etudiants);
        return ISessionMapper.toDTO(iSessionsRepository.save(session));
    }
}
