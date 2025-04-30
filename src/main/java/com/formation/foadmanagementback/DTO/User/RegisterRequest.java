package com.formation.foadmanagementback.DTO.User;

import com.formation.foadmanagementback.Enums.Roles;

public record RegisterRequest(

    String email,
    String password

) {
}
