package com.formation.foadmanagementback.DTO.Notification;

import java.util.UUID;

public record NotificationCreateDTO(

    String message,
    UUID destinataireUuid

) {
}
