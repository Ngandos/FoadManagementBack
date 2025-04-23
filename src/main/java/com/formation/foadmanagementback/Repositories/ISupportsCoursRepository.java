package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.SupportCour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISupportsCoursRepository extends JpaRepository<SupportCour, Long> {

    Optional<SupportCour> findByUuid(UUID uuid);
    List<SupportCour> findBySessionUuid(UUID sessionUuid);
    List<SupportCour> findByFormateurUuid(UUID formateurUuid);

}
