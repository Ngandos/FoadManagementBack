package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.Devoirs.DevoirDTO;
import com.formation.foadmanagementback.Entities.Devoir;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDevoirMapper {

    DevoirDTO toDTO(Devoir entity); // OK car simple

}
