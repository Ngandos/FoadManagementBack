package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Etudiant.EtudiantDTO;
import com.formation.foadmanagementback.DTO.User.UserDTO;
import com.formation.foadmanagementback.Entities.User;
import com.formation.foadmanagementback.Mappers.IEtudiantMapper;
import com.formation.foadmanagementback.Mappers.IUserMapper;
import com.formation.foadmanagementback.Repositories.IEtudiantsRepository;
import com.formation.foadmanagementback.Repositories.IUsersRepository;
import com.formation.foadmanagementback.Services.Abstracts.IUsersServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServImplements implements IUsersServ {

    private final IUsersRepository iUserRepository;
    private final IEtudiantsRepository iEtudiantsRepository;
    private final IEtudiantMapper iEtudiantMapper;
    private final IUserMapper iUserMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        return iUserRepository.findAll().stream()
            .map(iUserMapper::toDTO)
            .toList();
    }

    @Override
    public List<EtudiantDTO> getAllEtudiants() {
        return iEtudiantsRepository.findAll().stream()
                .map(iEtudiantMapper::toDTO) // ✅ ici
                .toList();
    }



    @Override
    public Optional<User> findByEmail(String email) {
        return iUserRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUuid(UUID uuid) {
        return iUserRepository.findByUuid(uuid);
    }

    @Override
    public boolean existsByEmail(String email) {
        return iUserRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return iUserRepository.save(user);
    }

}
