package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.DTO.RemiseDevoirDTO.RemiseDevoirCreateDTO;
import com.formation.foadmanagementback.DTO.RemiseDevoirDTO.RemiseDevoirDTO;
import com.formation.foadmanagementback.Entities.Devoir;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.RemiseDevoir;
import com.formation.foadmanagementback.Mappers.RemiseDevoirMapper;
import com.formation.foadmanagementback.Repositories.IDevoirsRepository;
import com.formation.foadmanagementback.Repositories.IEtudiantsRepository;
import com.formation.foadmanagementback.Repositories.IRemisesDevoirsRepository;
import com.formation.foadmanagementback.Services.Abstracts.IRemisesDevoirsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RemiseDevoirsServImplements implements IRemisesDevoirsServ {

    private final IRemisesDevoirsRepository iRemiseDevoirRepository;
    private final IDevoirsRepository iDevoirRepository;
    private final IEtudiantsRepository iEtudiantRepository;

    @Override
    public RemiseDevoirDTO create(RemiseDevoirCreateDTO dto) {
        Devoir devoir = iDevoirRepository.findByUuid(dto.devoirUuid())
            .orElseThrow(() -> new RuntimeException("Devoir non trouvé"));

        Etudiant etudiant = iEtudiantRepository.findByUuid(dto.etudiantUuid())
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        RemiseDevoir remise = RemiseDevoirMapper.toEntity(dto, devoir, etudiant);
        return RemiseDevoirMapper.toDTO(iRemiseDevoirRepository.save(remise));
    }

    @Override
    public RemiseDevoirDTO getByUuid(UUID uuid) {
        return iRemiseDevoirRepository.findByUuid(uuid)
            .map(RemiseDevoirMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Remise de devoir non trouvée"));
    }

    @Override
    public List<RemiseDevoirDTO> getByEtudiant(UUID etudiantUuid) {
        return iRemiseDevoirRepository.findByEtudiantUuid(etudiantUuid).stream()
            .map(RemiseDevoirMapper::toDTO)
            .toList();
    }

    @Override
    public List<RemiseDevoirDTO> getByDevoir(UUID devoirUuid) {
        return iRemiseDevoirRepository.findByDevoirUuid(devoirUuid).stream()
            .map(RemiseDevoirMapper::toDTO)
            .toList();
    }

}
