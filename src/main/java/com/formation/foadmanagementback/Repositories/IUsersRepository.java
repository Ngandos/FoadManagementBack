package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.User;
import com.formation.foadmanagementback.Enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUuid(UUID uuid);
    boolean existsByEmail(String email);
    List<User> findByRole(Roles roles);
}
