package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.Notification.NotificationCreateDTO;
import com.formation.foadmanagementback.DTO.Notification.NotificationDTO;

import java.util.List;
import java.util.UUID;

public interface INotificationsServ {

    List<NotificationDTO> getAllNotifications();
    List<NotificationDTO> getNonLuesParDestinataireUuid(UUID destinataireUuid);
    List<NotificationDTO> getRecentsParDestinataireUuid(UUID destinataireUuid, int limit);
    NotificationDTO getByUuid(UUID uuid);
    List<NotificationDTO> getByDestinataireUuid(UUID destinataireUuid);
    NotificationDTO createNotification(NotificationCreateDTO dto);
    void notifierUtilisateur(String message, UUID destinataireUuid);
    void notifierEtudiantsDeLaSession(String message, UUID sessionUuid);
    void notifierFormateurInscriptionEtudiant(UUID sessionUuid, String nomEtudiant);
    void notifierAdminsNouvelUtilisateur(String email, String role);
    void marquerCommeLue(UUID uuid);

}
