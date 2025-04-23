package com.formation.foadmanagementback.DTO.Devoirs;

import java.time.LocalDate;
import java.util.UUID;

public record DevoirCreateDTO(

    String titre,
    String consigne,
    String fichierUrl,
    LocalDate dateLimite,
    UUID sessionUuid,
    UUID formateurUuid

) {
}
