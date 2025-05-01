package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.RemiseDevoir.RemiseDevoirDTO;
import com.formation.foadmanagementback.Entities.RemiseDevoir;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-01T23:15:42+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class IRemiseDevoirMapperImpl implements IRemiseDevoirMapper {

    @Override
    public RemiseDevoirDTO toDTO(RemiseDevoir entity) {
        if ( entity == null ) {
            return null;
        }

        UUID uuid = null;
        UUID devoirUuid = null;
        UUID etudiantUuid = null;
        LocalDateTime dateRemise = null;
        String fichierUrl = null;
        String commentaire = null;

        RemiseDevoirDTO remiseDevoirDTO = new RemiseDevoirDTO( uuid, devoirUuid, etudiantUuid, dateRemise, fichierUrl, commentaire );

        return remiseDevoirDTO;
    }
}
