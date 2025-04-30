package com.formation.foadmanagementback.DTO.RemiseDevoir;

import java.time.LocalDateTime;
import java.util.UUID;

public record RemiseDevoirDTO(

    UUID uuid,
    UUID devoirUuid,
    UUID etudiantUuid,
    LocalDateTime dateRemise,
    String fichierUrl,
    String commentaire

) {
}
