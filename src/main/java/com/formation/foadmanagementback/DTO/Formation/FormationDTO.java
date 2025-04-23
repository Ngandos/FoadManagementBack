package com.formation.foadmanagementback.DTO.Formation;

import java.util.List;
import java.util.UUID;

public record FormationDTO(

    UUID uuid,
    String titre,
    String description,
    String niveau,
    UUID formateurUuid,
    UUID finalCertifUuid,
    List<UUID> etudiantsUuids

) {
}
