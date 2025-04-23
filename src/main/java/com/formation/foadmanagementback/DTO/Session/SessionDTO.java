package com.formation.foadmanagementback.DTO.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record SessionDTO(

    UUID uuid,
    String nom,
    LocalDate dateDebut,
    LocalDate dateFin,
    UUID formationUuid,
    List<UUID> etudiantsUuids

) {
}
