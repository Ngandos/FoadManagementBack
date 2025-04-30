package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.RemiseDevoir.RemiseDevoirCreateDTO;
import com.formation.foadmanagementback.DTO.RemiseDevoir.RemiseDevoirDTO;
import com.formation.foadmanagementback.Entities.Devoir;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.Formateur;
import com.formation.foadmanagementback.Entities.RemiseDevoir;
import com.formation.foadmanagementback.Mappers.IRemiseDevoirMapper;
import com.formation.foadmanagementback.Repositories.IDevoirsRepository;
import com.formation.foadmanagementback.Repositories.IEtudiantsRepository;
import com.formation.foadmanagementback.Repositories.IRemisesDevoirsRepository;
import com.formation.foadmanagementback.Services.Abstracts.INotificationsServ;
import com.formation.foadmanagementback.Services.Abstracts.IRemisesDevoirsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RemiseDevoirsServImplements implements IRemisesDevoirsServ {

    private final IRemisesDevoirsRepository iRemiseDevoirRepository;
    private final IDevoirsRepository iDevoirRepository;
    private final IEtudiantsRepository iEtudiantRepository;
    private final INotificationsServ iNotificationsServ;
    private final IRemiseDevoirMapper iRemiseDevoirMapper;

    @Override
    public RemiseDevoirDTO create(RemiseDevoirCreateDTO dto) {
        Devoir devoir = iDevoirRepository.findByUuid(dto.devoirUuid())
            .orElseThrow(() -> new RuntimeException("Devoir non trouvé"));

        Etudiant etudiant = iEtudiantRepository.findByUuid(dto.etudiantUuid())
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        RemiseDevoir remise = RemiseDevoir.builder()
            .devoir(devoir)
            .etudiant(etudiant)
            .fichierUrl(dto.fichierUrl())
            .commentaire(dto.commentaire())
            .dateRemise(LocalDateTime.now())
            .build();

        return iRemiseDevoirMapper.toDTO(iRemiseDevoirRepository.save(remise));
    }

    @Override
    public RemiseDevoirDTO getByUuid(UUID uuid) {
        return iRemiseDevoirRepository.findByUuid(uuid)
            .map(iRemiseDevoirMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Remise de devoir non trouvée"));
    }

    @Override
    public List<RemiseDevoirDTO> getByEtudiant(UUID etudiantUuid) {
        return iRemiseDevoirRepository.findByEtudiantUuid(etudiantUuid).stream()
            .map(iRemiseDevoirMapper::toDTO)
            .toList();
    }

    @Override
    public List<RemiseDevoirDTO> getByDevoir(UUID devoirUuid) {
        return iRemiseDevoirRepository.findByDevoirUuid(devoirUuid).stream()
            .map(iRemiseDevoirMapper::toDTO)
            .toList();
    }

    @Override
    public RemiseDevoirDTO rendreDevoir(RemiseDevoirCreateDTO dto) {
        Devoir devoir = iDevoirRepository.findByUuid(dto.devoirUuid())
            .orElseThrow(() -> new RuntimeException("Devoir non trouvé"));

        Etudiant etudiant = iEtudiantRepository.findByUuid(dto.etudiantUuid())
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        Formateur formateur = devoir.getFormateur();

        iNotificationsServ.notifierUtilisateur(
            "L'étudiant " + etudiant.getNom() + " a rendu son devoir : " + devoir.getTitre(),
            formateur.getUuid()
        );

        RemiseDevoir remise = RemiseDevoir.builder()
            .devoir(devoir)
            .etudiant(etudiant)
            .fichierUrl(dto.fichierUrl())
            .commentaire(dto.commentaire())
            .dateRemise(LocalDateTime.now())
            .build();

        return iRemiseDevoirMapper.toDTO(iRemiseDevoirRepository.save(remise));
    }
}
