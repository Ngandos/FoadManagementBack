package com.formation.foadmanagementback.DTO.Message;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDTO(

    UUID uuid,
    String contenu,
    LocalDateTime dateEnvoi,
    LocalDateTime dateReception,
    boolean lu,
    UUID expediteurUuid,
    UUID destinataireUuid

) {}
