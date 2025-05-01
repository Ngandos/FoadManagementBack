package com.formation.foadmanagementback.DTO.Message;

import java.util.UUID;

public record MessageCreateDTO(

    String contenu,
    UUID expediteurUuid,
    UUID destinataireUuid

) {}
