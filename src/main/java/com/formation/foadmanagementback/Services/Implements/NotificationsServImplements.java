package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Notification.NotificationCreateDTO;
import com.formation.foadmanagementback.DTO.Notification.NotificationDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.Notification;
import com.formation.foadmanagementback.Mappers.NotificationMapper;
import com.formation.foadmanagementback.Repositories.IEtudiantsRepository;
import com.formation.foadmanagementback.Repositories.INotificationsRepository;
import com.formation.foadmanagementback.Services.Abstracts.INotificationsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationsServImplements implements INotificationsServ {

    private final INotificationsRepository iNotificationsRepository;
    private final IEtudiantsRepository iEtudiantsRepository;

    @Override
    public List<NotificationDTO> getAllNotifications() {
        return iNotificationsRepository.findAll().stream()
            .map(NotificationMapper::toDTO)
            .toList();
    }

    @Override
    public NotificationDTO getByUuid(UUID uuid) {
        return iNotificationsRepository.findByUuid(uuid)
            .map(NotificationMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Notification non trouvée"));
    }

    @Override
    public List<NotificationDTO> getByDestinataireUuid(UUID destinataireUuid) {
        return iNotificationsRepository.findByDestinataireUuid(destinataireUuid).stream()
            .map(NotificationMapper::toDTO)
            .toList();
    }

    @Override
    public NotificationDTO createNotification(NotificationCreateDTO dto) {
        Etudiant destinataire = iEtudiantsRepository.findByUuid(dto.destinataireUuid())
        .orElseThrow(() -> new RuntimeException("Destinataire introuvable"));
        Notification notification = NotificationMapper.toEntity(dto, destinataire);
        Notification saved = iNotificationsRepository.save(notification);
        return NotificationMapper.toDTO(saved);
    }

    @Override
    public void marquerCommeLue(UUID uuid) {
        Notification notification = iNotificationsRepository.findByUuid(uuid)
        .orElseThrow(() -> new RuntimeException("Notification introuvable"));
        notification.setLu(true);
        iNotificationsRepository.save(notification);
    }

}
