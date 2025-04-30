package com.formation.foadmanagementback.Controllers;

import com.formation.foadmanagementback.DTO.Auth.AuthenticationRequest;
import com.formation.foadmanagementback.DTO.Auth.AuthenticationResponse;
import com.formation.foadmanagementback.DTO.User.RegisterRequest;
import com.formation.foadmanagementback.Services.Abstracts.IAuthServ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthController {

    private final IAuthServ authServ;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authServ.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authServ.authenticate(request));
    }

}
