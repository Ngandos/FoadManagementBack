package com.formation.foadmanagementback.DTO.SupportCours;

import java.util.UUID;

public record SupportCoursCreateDTO(

    String titre,
    String description,
    String fichierUrl,
    UUID sessionUuid,
    UUID formateurUuid

) {
}
