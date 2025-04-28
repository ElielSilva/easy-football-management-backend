package br.edu.ifpe.easy_football_management_backend.features.users;

import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.domain.entity.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected UserRepository userRepository;


    public User toEntity(UserDTO dto) {
        User entity =  User.builder()
                .name(dto.name())
                .email(dto.email())
                .phone(dto.phone())
                .urlImage(dto.urlImage())
                .build();

        return entity;
    }

    public UserDTO toDTO(User entity) {

        return new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getUrlImage(),
                entity.getTeam()
        );
    }
}
