package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.Notification.NotificationCreateDTO;
import com.formation.foadmanagementback.DTO.Notification.NotificationDTO;

import java.util.List;
import java.util.UUID;

public interface INotificationsServ {

    List<NotificationDTO> getAllNotifications();
    NotificationDTO getByUuid(UUID uuid);
    List<NotificationDTO> getByDestinataireUuid(UUID destinataireUuid);
    NotificationDTO createNotification(NotificationCreateDTO dto);
    void marquerCommeLue(UUID uuid);

}
