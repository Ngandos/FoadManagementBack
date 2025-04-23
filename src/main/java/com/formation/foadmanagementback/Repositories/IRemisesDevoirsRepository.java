package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.RemiseDevoir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRemisesDevoirsRepository extends JpaRepository<RemiseDevoir, Long> {

    Optional<RemiseDevoir> findByUuid(UUID uuid);
    List<RemiseDevoir> findByEtudiantUuid(UUID etudiantUuid);
    List<RemiseDevoir> findByDevoirUuid(UUID devoirUuid);

}
