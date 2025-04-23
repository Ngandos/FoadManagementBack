package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Message.MessageCreateDTO;
import com.formation.foadmanagementback.DTO.Message.MessageDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.Message;
import com.formation.foadmanagementback.Mappers.MessageMapper;
import com.formation.foadmanagementback.Repositories.IEtudiantsRepository;
import com.formation.foadmanagementback.Repositories.IMessagesRepository;
import com.formation.foadmanagementback.Services.Abstracts.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServImplements implements IMessageService {

    private final IMessagesRepository iMessagesRepository;
    private final IEtudiantsRepository iEtudiantsRepository;

    @Override
    public MessageDTO sendMessage(MessageCreateDTO dto) {
        Etudiant expediteur = iEtudiantsRepository.findByUuid(dto.expediteurUuid())
            .orElseThrow(() -> new RuntimeException("Expéditeur introuvable"));

        Etudiant destinataire = iEtudiantsRepository.findByUuid(dto.destinataireUuid())
            .orElseThrow(() -> new RuntimeException("Destinataire introuvable"));

        Message message = MessageMapper.toEntity(dto, expediteur, destinataire);
        return MessageMapper.toDTO(iMessagesRepository.save(message));
    }

    @Override
    public MessageDTO getByUuid(UUID uuid) {
        return iMessagesRepository.findByUuid(uuid)
            .map(MessageMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Message non trouvé"));
    }

    @Override
    public List<MessageDTO> getAllMessagesReceived(UUID destinataireUuid) {
        return iMessagesRepository.findByDestinataireUuid(destinataireUuid).stream()
            .map(MessageMapper::toDTO)
            .toList();
    }

    @Override
    public List<MessageDTO> getAllMessagesSent(UUID expediteurUuid) {
        return iMessagesRepository.findByExpediteurUuid(expediteurUuid).stream()
            .map(MessageMapper::toDTO)
            .toList();
    }

    @Override
    public List<MessageDTO> getConversation(UUID expediteurUuid, UUID destinataireUuid) {
        return iMessagesRepository.findByExpediteurUuidAndDestinataireUuid(expediteurUuid, destinataireUuid).stream()
            .map(MessageMapper::toDTO)
            .toList();
    }

    @Override
    public void marquerCommeLu(UUID messageUuid) {
        Message message = iMessagesRepository.findByUuid(messageUuid)
        .orElseThrow(() -> new RuntimeException("Message introuvable"));
        message.setLu(true);
        iMessagesRepository.save(message);
    }

}
