package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.SupportCours.SupportCoursCreateDTO;
import com.formation.foadmanagementback.DTO.SupportCours.SupportCoursDTO;
import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Entities.Session;
import com.formation.foadmanagementback.Entities.SupportCour;
import com.formation.foadmanagementback.Mappers.SupportCoursMapper;
import com.formation.foadmanagementback.Repositories.IFormateursRepository;
import com.formation.foadmanagementback.Repositories.ISessionsRepository;
import com.formation.foadmanagementback.Repositories.ISupportsCoursRepository;
import com.formation.foadmanagementback.Services.Abstracts.ISupportsCoursServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportsCoursServImplements implements ISupportsCoursServ {

    private final ISupportsCoursRepository iSupportsCoursRepository;
    private final ISessionsRepository iSessionsRepository;
    private final IFormateursRepository iFormateursRepository;

    @Override
    public SupportCoursDTO create(SupportCoursCreateDTO dto) {
        Session session = iSessionsRepository.findByUuid(dto.sessionUuid())
            .orElseThrow(() -> new RuntimeException("Session non trouvée"));
        Formateur formateur = iFormateursRepository.findByUuid(dto.formateurUuid())
            .orElseThrow(() -> new RuntimeException("Formateur non trouvé"));

        SupportCour entity = SupportCoursMapper.toEntity(dto, session, formateur);
        return SupportCoursMapper.toDTO(iSupportsCoursRepository.save(entity));
    }

    @Override
    public List<SupportCoursDTO> getAll() {
        return iSupportsCoursRepository.findAll().stream()
            .map(SupportCoursMapper::toDTO)
            .toList();
    }

    @Override
    public SupportCoursDTO getByUuid(UUID uuid) {
        return iSupportsCoursRepository.findByUuid(uuid)
            .map(SupportCoursMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Support de cours non trouvé"));
    }

    @Override
    public List<SupportCoursDTO> getBySession(UUID sessionUuid) {
        return iSupportsCoursRepository.findBySessionUuid(sessionUuid).stream()
            .map(SupportCoursMapper::toDTO)
            .toList();
    }

    @Override
    public List<SupportCoursDTO> getByFormateur(UUID formateurUuid) {
        return iSupportsCoursRepository.findByFormateurUuid(formateurUuid).stream()
            .map(SupportCoursMapper::toDTO)
            .toList();
    }
}
