package com.formation.foadmanagementback.Mappers;

import com.formation.foadmanagementback.DTO.User.UserCreateDTO;
import com.formation.foadmanagementback.DTO.User.UserDTO;
import com.formation.foadmanagementback.Entities.User;
import com.formation.foadmanagementback.Enums.Roles;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-01T23:15:42+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UUID uuid = null;
        String email = null;
        Roles role = null;

        UserDTO userDTO = new UserDTO( uuid, email, role );

        return userDTO;
    }

    @Override
    public User toEntity(UserCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        return user;
    }
}
