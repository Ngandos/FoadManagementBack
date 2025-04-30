package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Notification.NotificationCreateDTO;
import com.formation.foadmanagementback.DTO.Notification.NotificationDTO;
import com.formation.foadmanagementback.Entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface INotificationMapper {

    default NotificationDTO toDTO(Notification entity) {
        return new NotificationDTO(
            entity.getUuid(),
            entity.getMessage(),
            entity.getDateEnvoi(),
            entity.isLu(),
            entity.getDestinataireEtudiant() != null ? entity.getDestinataireEtudiant().getUuid() : null,
            entity.getDestinataireFormateur() != null ? entity.getDestinataireFormateur().getUuid() : null,
            entity.getDestinataireUser() != null ? entity.getDestinataireUser().getUuid() : null
        );
    }
}
