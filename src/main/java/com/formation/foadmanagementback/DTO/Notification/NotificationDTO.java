package com.formation.foadmanagementback.DTO.Notification;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationDTO(

    UUID uuid,
    String message,
    LocalDateTime dateEnvoi,
    boolean lu,
    UUID destinataireEtudiantUuid,
    UUID destinataireFormateurUuid,
    UUID destinataireUserUuid

) {
}
