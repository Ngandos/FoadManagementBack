package com.formation.foadmanagementback.DTO.User;

import com.formation.foadmanagementback.Enums.Roles;

public record UserCreateDTO(

    String email,
    String password,
    Roles role

) {
}
