package com.formation.foadmanagementback.DTO.Auth;

public record AuthenticationRequest(

        String email,
        String password

) {
}
