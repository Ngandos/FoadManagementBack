package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IFormationsRepository extends JpaRepository<Formation, Long> {

    Optional<Formation> findByUuid(UUID uuid);

}
