package com.formation.foadmanagementback.Services.Abstracts;

import com.formation.foadmanagementback.DTO.Message.MessageCreateDTO;
import com.formation.foadmanagementback.DTO.Message.MessageDTO;

import java.util.List;
import java.util.UUID;

public interface IMessagesServ {

    MessageDTO sendMessage(MessageCreateDTO dto);

    MessageDTO getByUuid(UUID uuid);

    List<MessageDTO> getAllMessagesReceived(UUID destinataireUuid);

    List<MessageDTO> getAllMessagesSent(UUID expediteurUuid);

    List<MessageDTO> getConversation(UUID expediteurUuid, UUID destinataireUuid);

    List<MessageDTO> getMessagesNonLus(UUID destinataireUuid);

    void marquerCommeLu(UUID messageUuid);

}
