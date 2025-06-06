package br.edu.ifpe.easy_football_management_backend.features.championships;


import br.edu.ifpe.easy_football_management_backend.domain.entity.Championships;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.domain.entity.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChampionshipsMapper {

    @Autowired
    protected UserRepository userRepository;

    @Mapping(source = "type", target = "typeChampionship")
    @Mapping(source = "status", target = "statusChampionship")
    @Mapping(source = "user.id", target = "userID")
    @Mapping(target = "img", ignore = true)
    public abstract ChampionshipsDTO toDto(Championships championships);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "typeChampionship", target = "type")
    @Mapping(source = "statusChampionship", target = "status")
    @Mapping(target = "user", expression = "java(findUserById(dto.userID()))")
    @Mapping(target = "description", source = "description", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
    public abstract Championships toEntity(ChampionshipsDTO dto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "typeChampionship", target = "type")
    @Mapping(source = "statusChampionship", target = "status")
    @Mapping(target = "user", expression = "java(findUserById(dto.userID()))")
    @Mapping(target = "description", source = "description", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
    public abstract void updateChampionshipsFromDto(ChampionshipsDTO dto, @MappingTarget Championships championships);


    protected User findUserById(UUID userId) {
        if (userId == null) {
            return null;
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found."));
    }

}

