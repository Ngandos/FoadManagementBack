package com.formation.foadmanagementback.DTO.SupportCours;

import java.time.LocalDateTime;
import java.util.UUID;

public record SupportCoursDTO(

    UUID uuid,
    String titre,
    String description,
    String fichierUrl,
    LocalDateTime datePublication,
    UUID sessionUuid,
    UUID formateurUuid

) {
}
