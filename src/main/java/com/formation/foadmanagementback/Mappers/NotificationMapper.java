package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Notification.NotificationCreateDTO;
import com.formation.foadmanagementback.DTO.Notification.NotificationDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.Notification;

import java.time.LocalDateTime;

public class NotificationMapper {

    public static NotificationDTO toDTO(Notification entity) {
        return new NotificationDTO(
            entity.getUuid(),
            entity.getMessage(),
            entity.getDateEnvoi(),
            entity.isLu(),
            entity.getDestinataire().getUuid()
        );
    }

    public static Notification toEntity(NotificationCreateDTO dto, Etudiant destinataire) {
        return Notification.builder()
            .message(dto.message())
            .dateEnvoi(LocalDateTime.now())
            .lu(false)
            .destinataire(destinataire)
            .build();
    }

}
