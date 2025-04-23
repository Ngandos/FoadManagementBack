package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface INotificationsRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByUuid(UUID uuid);

    List<Notification> findByDestinataireUuid(UUID destinataireUuid);

}
