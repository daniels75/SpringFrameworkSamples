package org.daniels.spring.cache.mapper;

import org.daniels.spring.cache.domain.User;
import org.daniels.spring.cache.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toUser(UserDTO userDTO);
}
