package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Entities.Formation;
import com.formation.foadmanagementback.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IFormateursRepository extends JpaRepository<Formateur, Long> {

    Optional<Formateur> findByUuid(UUID uuid);

    Optional<Formateur> findByUser(User user);

}
