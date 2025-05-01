package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Etudiant.EtudiantCreateDTO;
import com.formation.foadmanagementback.DTO.Etudiant.EtudiantDTO;
import com.formation.foadmanagementback.Entities.Etudiant;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-01T23:15:42+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class IEtudiantMapperImpl implements IEtudiantMapper {

    @Override
    public EtudiantDTO toDTO(Etudiant entity) {
        if ( entity == null ) {
            return null;
        }

        UUID uuid = null;
        String nom = null;
        String prenom = null;
        String email = null;
        String telephone = null;

        EtudiantDTO etudiantDTO = new EtudiantDTO( uuid, nom, prenom, email, telephone );

        return etudiantDTO;
    }

    @Override
    public Etudiant toEntity(EtudiantCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Etudiant etudiant = new Etudiant();

        return etudiant;
    }
}
