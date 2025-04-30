package com.formation.foadmanagementback.DTO.User;

import com.formation.foadmanagementback.Enums.Roles;

import java.util.UUID;

public record UserDTO(

    UUID uuid,
    String email,
    Roles role


) {
}
