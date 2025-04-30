package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.Etudiant.EtudiantDTO;
import com.formation.foadmanagementback.DTO.User.UserDTO;
import com.formation.foadmanagementback.Entities.User;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface IUsersServ {

    List<UserDTO> getAllUsers();
    List<EtudiantDTO> getAllEtudiants();
    Optional<User> findByEmail(String email);
    Optional<User> findByUuid(UUID uuid);
    boolean existsByEmail(String email);
    User save(User user);

}
