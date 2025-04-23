package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Devoirs.DevoirCreateDTO;
import com.formation.foadmanagementback.DTO.Devoirs.DevoirDTO;
import com.formation.foadmanagementback.Entities.Devoir;
import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Entities.Session;
import com.formation.foadmanagementback.Mappers.DevoirMapper;
import com.formation.foadmanagementback.Repositories.IDevoirsRepository;
import com.formation.foadmanagementback.Repositories.IFormateursRepository;
import com.formation.foadmanagementback.Repositories.ISessionsRepository;
import com.formation.foadmanagementback.Services.Abstracts.IDevoirsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DevoirServImplements implements IDevoirsServ {

    private final IDevoirsRepository iDevoirRepository;
    private final ISessionsRepository iSessionRepository;
    private final IFormateursRepository iFormateurRepository;

    @Override
    public DevoirDTO create(DevoirCreateDTO dto) {
        Session session = iSessionRepository.findByUuid(dto.sessionUuid())
            .orElseThrow(() -> new RuntimeException("Session non trouvée"));
        Formateur formateur = iFormateurRepository.findByUuid(dto.formateurUuid())
            .orElseThrow(() -> new RuntimeException("Formateur non trouvé"));

        Devoir devoir = DevoirMapper.toEntity(dto, session, formateur);
        return DevoirMapper.toDTO(iDevoirRepository.save(devoir));
    }

    @Override
    public List<DevoirDTO> getAll() {
        return iDevoirRepository.findAll().stream()
            .map(DevoirMapper::toDTO)
            .toList();
    }

    @Override
    public DevoirDTO getByUuid(UUID uuid) {
        return iDevoirRepository.findByUuid(uuid)
            .map(DevoirMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Devoir non trouvé"));
    }

    @Override
    public List<DevoirDTO> getBySession(UUID sessionUuid) {
        return iDevoirRepository.findBySessionUuid(sessionUuid).stream()
            .map(DevoirMapper::toDTO)
            .toList();
    }

    @Override
    public List<DevoirDTO> getByFormateur(UUID formateurUuid) {
        return iDevoirRepository.findByFormateurUuid(formateurUuid).stream()
            .map(DevoirMapper::toDTO)
            .toList();
    }

}
