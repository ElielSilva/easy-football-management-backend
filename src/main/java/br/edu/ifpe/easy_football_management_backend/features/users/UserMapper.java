package br.edu.ifpe.easy_football_management_backend.features.users;

import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "entity.id")
    UserDTO toDTO(User entity);
}
