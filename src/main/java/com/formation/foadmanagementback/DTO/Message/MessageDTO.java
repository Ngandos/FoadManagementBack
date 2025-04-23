package com.formation.foadmanagementback.DTO.Message;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDTO(

    UUID uuid,
    UUID expediteurUuid,
    UUID destinataireUuid,
    String contenu,
    LocalDateTime dateEnvoi,
    boolean lu

) {
}
