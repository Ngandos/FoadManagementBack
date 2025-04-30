package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.Notification.NotificationCreateDTO;
import com.formation.foadmanagementback.DTO.Notification.NotificationDTO;
import com.formation.foadmanagementback.Entities.*;
import com.formation.foadmanagementback.Enums.Roles;
import com.formation.foadmanagementback.Mappers.INotificationMapper;
import com.formation.foadmanagementback.Repositories.*;
import com.formation.foadmanagementback.Services.Abstracts.INotificationsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationsServImplements implements INotificationsServ {

    private final INotificationsRepository iNotificationsRepository;
    private final IFormateursRepository iFormateursRepository;
    private final ISessionsRepository iSessionsRepository;
    private final IEtudiantsRepository iEtudiantsRepository;
    private final INotificationMapper iNotificationMapper;
    private final IUsersRepository iUsersRepository;

    @Override
    public List<NotificationDTO> getAllNotifications() {
        return iNotificationsRepository.findAll().stream()
            .map(iNotificationMapper::toDTO)
            .toList();
    }

    @Override
    public NotificationDTO getByUuid(UUID uuid) {
        return iNotificationsRepository.findByUuid(uuid)
            .map(iNotificationMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Notification non trouvée"));
    }

    @Override
    public List<NotificationDTO> getByDestinataireUuid(UUID destinataireUuid) {
        return iNotificationsRepository.findByDestinataireEtudiant_Uuid(destinataireUuid).stream()
            .map(iNotificationMapper::toDTO)
            .toList();
    }

    @Override
    public NotificationDTO createNotification(NotificationCreateDTO dto) {
        Etudiant destinataire = iEtudiantsRepository.findByUuid(dto.destinataireUuid())
            .orElseThrow(() -> new RuntimeException("Destinataire introuvable"));

        Notification notification = Notification.builder()
            .message(dto.message())
            .lu(false)
            .dateEnvoi(LocalDateTime.now())
            .destinataireEtudiant(destinataire)
            .build();

        return iNotificationMapper.toDTO(iNotificationsRepository.save(notification));
    }

    @Override
    public void marquerCommeLue(UUID uuid) {
        Notification notification = iNotificationsRepository.findByUuid(uuid)
            .orElseThrow(() -> new RuntimeException("Notification introuvable"));
        notification.setLu(true);
        iNotificationsRepository.save(notification);
    }

    @Override
    public List<NotificationDTO> getNonLuesParDestinataireUuid(UUID destinataireUuid) {
        List<Notification> notifs = iNotificationsRepository.findByDestinataireEtudiant_UuidAndLuFalse(destinataireUuid);
        if (notifs.isEmpty()) {
            notifs = iNotificationsRepository.findByDestinataireFormateur_UuidAndLuFalse(destinataireUuid);
        }
        return notifs.stream().map(iNotificationMapper::toDTO).toList();
    }

    @Override
    public List<NotificationDTO> getRecentsParDestinataireUuid(UUID destinataireUuid, int limit) {
        List<Notification> notifs = iNotificationsRepository
            .findTop5ByDestinataireEtudiant_UuidOrderByDateEnvoiDesc(destinataireUuid);

        if (notifs.isEmpty()) {
            notifs = iNotificationsRepository
                .findTop5ByDestinataireFormateur_UuidOrderByDateEnvoiDesc(destinataireUuid);
        }

        return notifs.stream().limit(limit).map(iNotificationMapper::toDTO).toList();
    }

    @Override
    public void notifierUtilisateur(String message, UUID destinataireUuid) {

        Notification.NotificationBuilder builder = Notification.builder()
            .message(message)
            .lu(false)
            .dateEnvoi(LocalDateTime.now());

        // Chercher si c’est un étudiant
        iEtudiantsRepository.findByUuid(destinataireUuid).ifPresentOrElse(
            etudiant -> {
                Notification notif = builder.destinataireEtudiant(etudiant).build();
                iNotificationsRepository.save(notif);
            },
            () -> {
                // Sinon essayer avec les formateurs
                iFormateursRepository.findByUuid(destinataireUuid).ifPresent(formateur -> {
                    Notification notif = builder.destinataireFormateur(formateur).build();
                    iNotificationsRepository.save(notif);
                });
            }
        );
    }

    @Override
    public void notifierEtudiantsDeLaSession(String message, UUID sessionUuid) {
        // Tu récupères tous les étudiants liés à la session
        List<Etudiant> etudiants = iSessionsRepository.findByUuid(sessionUuid)
            .orElseThrow(() -> new RuntimeException("Session introuvable"))
            .getEtudiants(); // Assure-toi que getEtudiants() existe et fonctionne

        // Pour chaque étudiant, créer une notification
        etudiants.forEach(etudiant -> {
            Notification notification = Notification.builder()
                .message(message)
                .lu(false)
                .dateEnvoi(LocalDateTime.now())
                .destinataireEtudiant(etudiant)
                .build();
            iNotificationsRepository.save(notification);
        });
    }

    @Override
    public void notifierFormateurInscriptionEtudiant(UUID sessionUuid, String nomEtudiant) {
        Session session = iSessionsRepository.findByUuid(sessionUuid)
                .orElseThrow(() -> new RuntimeException("Session introuvable"));

        Formateur formateur = session.getFormateur();

        if (formateur != null && formateur.getUser() != null) {
            Notification notification = Notification.builder()
                    .message("L'étudiant " + nomEtudiant + " a été inscrit à la session : " + session.getNom())
                    .lu(false)
                    .dateEnvoi(LocalDateTime.now())
                    .destinataireUser(formateur.getUser()) // ✅ Utilisation du champ générique
                    .build();

            iNotificationsRepository.save(notification);
        }
    }


    @Override
    public void notifierAdminsNouvelUtilisateur(String email, String role) {
        List<User> admins = iUsersRepository.findByRole(Roles.ADMIN);

        admins.forEach(admin -> {
            Notification notification = Notification.builder()
                .message("Un nouveau compte a été créé : " + email + " (rôle : " + role + ")")
                .lu(false)
                .dateEnvoi(LocalDateTime.now())
                .destinataireUser(admin) // ✅ Utilisation du champ User générique
                .build();
            iNotificationsRepository.save(notification);
        });
    }


}

