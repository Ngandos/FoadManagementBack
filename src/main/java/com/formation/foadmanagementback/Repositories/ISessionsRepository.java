package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ISessionsRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByUuid(UUID uuid);

}
