package com.formation.foadmanagementback.Services.Implements;


import com.formation.foadmanagementback.DTO.Auth.AuthenticationRequest;
import com.formation.foadmanagementback.DTO.Auth.AuthenticationResponse;
import com.formation.foadmanagementback.DTO.User.RegisterRequest;
import com.formation.foadmanagementback.Entities.User;
import com.formation.foadmanagementback.Repositories.IUsersRepository;
import com.formation.foadmanagementback.Security.JwtUtils;
import com.formation.foadmanagementback.Services.Abstracts.IAuthServ;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.formation.foadmanagementback.Enums.Roles;

@Service
@RequiredArgsConstructor
public class AuthServImplements implements IAuthServ {

    private final IUsersRepository iUsersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .role(Roles.VISITEUR) // 🔐 FORCÉ !
            .build();

        iUsersRepository.save(user);
        String token = jwtUtils.generateToken(user);
        return new AuthenticationResponse(token);
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        User user = iUsersRepository.findByEmail(request.email())
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        String token = jwtUtils.generateToken(user);
        return new AuthenticationResponse(token);
    }

}
