package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Message.MessageCreateDTO;
import com.formation.foadmanagementback.DTO.Message.MessageDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.Message;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface IMessageMapper {

    public static MessageDTO toDTO(Message entity) {
        return new MessageDTO(
            entity.getUuid(),
            entity.getExpediteur().getUuid(),
            entity.getDestinataire().getUuid(),
            entity.getContenu(),
            entity.getDateEnvoi(),
            entity.isLu()
        );
    }

    public static Message toEntity(MessageCreateDTO dto, Etudiant expediteur, Etudiant destinataire) {
        return Message.builder()
            .expediteur(expediteur)
            .destinataire(destinataire)
            .contenu(dto.contenu())
            .dateEnvoi(LocalDateTime.now())
            .lu(false)
            .build();
    }

}
