package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Devoirs.DevoirCreateDTO;
import com.formation.foadmanagementback.DTO.Devoirs.DevoirDTO;
import com.formation.foadmanagementback.Entities.Devoir;
import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Entities.Session;
import com.formation.foadmanagementback.Mappers.IDevoirMapper;
import com.formation.foadmanagementback.Repositories.IDevoirsRepository;
import com.formation.foadmanagementback.Repositories.IFormateursRepository;
import com.formation.foadmanagementback.Repositories.ISessionsRepository;
import com.formation.foadmanagementback.Services.Abstracts.IDevoirsServ;
import com.formation.foadmanagementback.Services.Abstracts.INotificationsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DevoirServImplements implements IDevoirsServ {

    private final IFormateursRepository iFormateursRepository;
    private final INotificationsServ iNotificationsServ;
    private final ISessionsRepository iSessionsRepository;
    private final IDevoirsRepository iDevoirsRepository;
    private final IDevoirMapper iDevoirMapper;

    @Override
    public DevoirDTO create(DevoirCreateDTO dto) {
        Session session = iSessionsRepository.findByUuid(dto.sessionUuid())
                .orElseThrow(() -> new RuntimeException("Session non trouvée"));
        Formateur formateur = iFormateursRepository.findByUuid(dto.formateurUuid())
                .orElseThrow(() -> new RuntimeException("Formateur non trouvé"));

        // Mapping manuel de l'entité
        Devoir entity = Devoir.builder()
                .titre(dto.titre())
                .consigne(dto.consigne())
                .fichierUrl(dto.fichierUrl())
                .dateLimite(dto.dateLimite())
                .session(session)
                .formateur(formateur)
                .build();

        Devoir saved = iDevoirsRepository.save(entity);

        // Notification automatique
        iNotificationsServ.notifierEtudiantsDeLaSession(
                "Un nouveau devoir a été publié : " + saved.getTitre(),
                session.getUuid()
        );

        return iDevoirMapper.toDTO(saved);
    }

    @Override
    public List<DevoirDTO> getAll() {
        return iDevoirsRepository.findAll().stream()
                .map(iDevoirMapper::toDTO)
                .toList();
    }

    @Override
    public DevoirDTO getByUuid(UUID uuid) {
        return iDevoirsRepository.findByUuid(uuid)
                .map(iDevoirMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Devoir non trouvé"));
    }

    @Override
    public List<DevoirDTO> getBySession(UUID sessionUuid) {
        return iDevoirsRepository.findBySessionUuid(sessionUuid).stream()
                .map(iDevoirMapper::toDTO)
                .toList();
    }

    @Override
    public List<DevoirDTO> getByFormateur(UUID formateurUuid) {
        return iDevoirsRepository.findByFormateurUuid(formateurUuid).stream()
                .map(iDevoirMapper::toDTO)
                .toList();
    }
}
