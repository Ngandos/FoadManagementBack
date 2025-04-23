package com.formation.foadmanagementback.DTO.Message;

import java.util.UUID;

public record MessageCreateDTO(

    UUID expediteurUuid,
    UUID destinataireUuid,
    String contenu

) {
}
