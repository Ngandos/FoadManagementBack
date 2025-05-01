package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Message.MessageCreateDTO;
import com.formation.foadmanagementback.DTO.Message.MessageDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Entities.Message;
import com.formation.foadmanagementback.Entities.User;
import com.formation.foadmanagementback.Repositories.IEtudiantsRepository;
import com.formation.foadmanagementback.Repositories.IFormateursRepository;
import com.formation.foadmanagementback.Repositories.IMessagesRepository;
import com.formation.foadmanagementback.Repositories.IUsersRepository;
import com.formation.foadmanagementback.Services.Abstracts.IMessagesServ;
import com.formation.foadmanagementback.Services.Abstracts.INotificationsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServImplements implements IMessagesServ {

    private final IMessagesRepository iMessagesRepository;
    private final IUsersRepository iUsersRepository;
    private final IEtudiantsRepository iEtudiantsRepository;
    private final IFormateursRepository iFormateursRepository;
    private final INotificationsServ iNotificationsServ;

    @Override
    public MessageDTO sendMessage(MessageCreateDTO dto) {
        User expediteur = iUsersRepository.findByUuid(dto.expediteurUuid())
            .orElseThrow(() -> new RuntimeException("Expéditeur introuvable"));

        User destinataire = iUsersRepository.findByUuid(dto.destinataireUuid())
            .orElseThrow(() -> new RuntimeException("Destinataire introuvable"));

        String prenomExpediteur = "";
        String nomExpediteur = "";

        if (expediteur.getRole().name().equals("ETUDIANT")) {
            Etudiant etudiant = iEtudiantsRepository.findByUuid(expediteur.getUuid())
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));
            prenomExpediteur = etudiant.getPrenom();
            nomExpediteur = etudiant.getNom();
        } else if (expediteur.getRole().name().equals("FORMATEUR")) {
            Formateur formateur = iFormateursRepository.findByUuid(expediteur.getUuid())
                .orElseThrow(() -> new RuntimeException("Formateur introuvable"));
            prenomExpediteur = formateur.getPrenom();
            nomExpediteur = formateur.getNom();
        } else {
            prenomExpediteur = "Utilisateur";
        }

        Message message = Message.builder()
            .uuid(UUID.randomUUID())
            .contenu(dto.contenu())
            .expediteur(expediteur)
            .destinataire(destinataire)
            .lu(false)
            .build();

        Message saved = iMessagesRepository.save(message);

        iNotificationsServ.notifierUtilisateur(
            "Vous avez reçu un nouveau message de " + prenomExpediteur + " " + nomExpediteur,
            destinataire.getUuid()
        );

        return new MessageDTO(
            saved.getUuid(),
            saved.getContenu(),
            saved.getDateEnvoi(),
            saved.getDateReception(),
            saved.isLu(),
            saved.getExpediteur().getUuid(),
            saved.getDestinataire().getUuid()
        );
    }

    @Override
    public MessageDTO getByUuid(UUID uuid) {
        return iMessagesRepository.findByUuid(uuid)
            .map(saved -> new MessageDTO(
                saved.getUuid(),
                saved.getContenu(),
                saved.getDateEnvoi(),
                saved.getDateReception(),
                saved.isLu(),
                saved.getExpediteur().getUuid(),
                saved.getDestinataire().getUuid()
            ))
            .orElseThrow(() -> new RuntimeException("Message non trouvé"));
    }

    @Override
    public List<MessageDTO> getAllMessagesReceived(UUID destinataireUuid) {
        return iMessagesRepository.findByDestinataireUuid(destinataireUuid).stream()
            .map(saved -> new MessageDTO(
                saved.getUuid(),
                saved.getContenu(),
                saved.getDateEnvoi(),
                saved.getDateReception(),
                saved.isLu(),
                saved.getExpediteur().getUuid(),
                saved.getDestinataire().getUuid()
            ))
            .toList();
    }

    @Override
    public List<MessageDTO> getAllMessagesSent(UUID expediteurUuid) {
        return iMessagesRepository.findByExpediteurUuid(expediteurUuid).stream()
            .map(saved -> new MessageDTO(
                saved.getUuid(),
                saved.getContenu(),
                saved.getDateEnvoi(),
                saved.getDateReception(),
                saved.isLu(),
                saved.getExpediteur().getUuid(),
                saved.getDestinataire().getUuid()
            ))
            .toList();
    }

    @Override
    public List<MessageDTO> getConversation(UUID expediteurUuid, UUID destinataireUuid) {
        return iMessagesRepository.findByExpediteurUuidAndDestinataireUuid(expediteurUuid, destinataireUuid).stream()
            .map(saved -> new MessageDTO(
                saved.getUuid(),
                saved.getContenu(),
                saved.getDateEnvoi(),
                saved.getDateReception(),
                saved.isLu(),
                saved.getExpediteur().getUuid(),
                saved.getDestinataire().getUuid()
            ))
            .toList();
    }

    @Override
    public List<MessageDTO> getMessagesNonLus(UUID destinataireUuid) {
        return iMessagesRepository.findByDestinataireUuidAndLuFalse(destinataireUuid).stream()
            .map(saved -> new MessageDTO(
                saved.getUuid(),
                saved.getContenu(),
                saved.getDateEnvoi(),
                saved.getDateReception(),
                saved.isLu(),
                saved.getExpediteur().getUuid(),
                saved.getDestinataire().getUuid()
            ))
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
