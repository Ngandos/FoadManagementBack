package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.Devoir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDevoirsRepository extends JpaRepository<Devoir, Long> {

    Optional<Devoir> findByUuid(UUID uuid);
    List<Devoir> findBySessionUuid(UUID sessionUuid);
    List<Devoir> findByFormateurUuid(UUID formateurUuid);

}
