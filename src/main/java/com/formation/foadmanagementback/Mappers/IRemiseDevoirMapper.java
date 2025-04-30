package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.RemiseDevoir.RemiseDevoirCreateDTO;
import com.formation.foadmanagementback.DTO.RemiseDevoir.RemiseDevoirDTO;
import com.formation.foadmanagementback.Entities.Devoir;
import com.formation.foadmanagementback.Entities.Etudiant;
import com.formation.foadmanagementback.Entities.RemiseDevoir;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IRemiseDevoirMapper {

    RemiseDevoirDTO toDTO(RemiseDevoir entity);

}
