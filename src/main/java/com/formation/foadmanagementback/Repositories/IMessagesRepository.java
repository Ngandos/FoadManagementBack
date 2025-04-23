package com.formation.foadmanagementback.Repositories;

import com.formation.foadmanagementback.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMessagesRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByUuid(UUID uuid);

    List<Message> findByExpediteurUuid(UUID expediteurUuid);
    List<Message> findByDestinataireUuid(UUID destinataireUuid);
    List<Message> findByExpediteurUuidAndDestinataireUuid(UUID expediteurUuid, UUID destinataireUuid);

}
