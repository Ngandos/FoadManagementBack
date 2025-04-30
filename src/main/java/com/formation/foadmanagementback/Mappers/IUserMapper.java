package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.User.UserCreateDTO;
import com.formation.foadmanagementback.DTO.User.UserDTO;
import com.formation.foadmanagementback.Entities.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IUserMapper {

    UserDTO toDTO(User user);
    User toEntity(UserCreateDTO dto);

}
