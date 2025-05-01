package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IEtudiantsRepository extends JpaRepository<Etudiant, Long> {

    Optional<Etudiant> findByUuid(UUID uuid);

    Optional<Etudiant> findByUser(User user);

}
