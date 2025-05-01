package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Devoirs.DevoirDTO;
import com.formation.foadmanagementback.Entities.Devoir;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-01T23:15:42+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class IDevoirMapperImpl implements IDevoirMapper {

    @Override
    public DevoirDTO toDTO(Devoir entity) {
        if ( entity == null ) {
            return null;
        }

        UUID uuid = null;
        String titre = null;
        String consigne = null;
        String fichierUrl = null;
        LocalDate dateLimite = null;
        UUID sessionUuid = null;
        UUID formateurUuid = null;

        DevoirDTO devoirDTO = new DevoirDTO( uuid, titre, consigne, fichierUrl, dateLimite, sessionUuid, formateurUuid );

        return devoirDTO;
    }
}
