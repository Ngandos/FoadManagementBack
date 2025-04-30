package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface INotificationsRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByUuid(UUID uuid);

    List<Notification> findByDestinataireEtudiant_Uuid(UUID destinataireUuid);

    List<Notification> findByDestinataireFormateur_Uuid(UUID destinataireUuid);

    List<Notification> findByDestinataireEtudiant_UuidAndLuFalse(UUID uuid);

    List<Notification> findTop5ByDestinataireEtudiant_UuidOrderByDateEnvoiDesc(UUID uuid);

    List<Notification> findByDestinataireFormateur_UuidAndLuFalse(UUID uuid);

    List<Notification> findTop5ByDestinataireFormateur_UuidOrderByDateEnvoiDesc(UUID uuid);

}
