package com.formation.foadmanagementback.DTO.RemiseDevoir;

import java.util.UUID;

public record RemiseDevoirCreateDTO(

    UUID devoirUuid,
    UUID etudiantUuid,
    String fichierUrl,
    String commentaire

) {
}
