package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICertificationsRepository extends JpaRepository<Certification, Long> {

    Optional<Certification> findByUuid(UUID uuid);

}
