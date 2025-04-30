package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.Auth.AuthenticationRequest;
import com.formation.foadmanagementback.DTO.Auth.AuthenticationResponse;
import com.formation.foadmanagementback.DTO.User.RegisterRequest;

public interface IAuthServ {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);

}
