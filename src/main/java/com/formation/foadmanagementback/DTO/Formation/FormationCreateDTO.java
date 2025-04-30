package com.formation.foadmanagementback.DTO.Formation;

import java.util.List;
import java.util.UUID;

public record FormationCreateDTO(

    String titre,
    String description,
    String niveau,
    UUID formateurUuid,
    UUID finalCertifUuid,
    UUID prerequisCertifUuid,
    List<UUID> etudiantsUuids

) {
}
