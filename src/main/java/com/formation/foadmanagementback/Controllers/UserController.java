package com.formation.foadmanagementback.Controllers;


import com.formation.foadmanagementback.DTO.User.UserDTO;
import com.formation.foadmanagementback.DTO.User.UserUpdateRoleDTO;
import com.formation.foadmanagementback.Entities.User;
import com.formation.foadmanagementback.Mappers.IUserMapper;
import com.formation.foadmanagementback.Services.Abstracts.IUsersServ;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@SecurityRequirement(name = "BearerAuth")
public class UserController {

    private final IUsersServ iUsersServ;
    private final IUserMapper iUserMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(iUsersServ.getAllUsers());
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> getByUuid(@PathVariable UUID uuid) {
        return iUsersServ.findByUuid(uuid);
    }

    @GetMapping("/email/{email}")
    public Optional<User> getByEmail(@PathVariable String email) {
        return iUsersServ.findByEmail(email);
    }

    @PatchMapping("/{uuid}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateUserRole(@PathVariable UUID uuid, @RequestBody UserUpdateRoleDTO dto) {

        User user = iUsersServ.findByUuid(uuid)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        user.setRole(dto.role());
        iUsersServ.save(user);

        return ResponseEntity.ok("Rôle mis à jour avec succès.");
    }

}
