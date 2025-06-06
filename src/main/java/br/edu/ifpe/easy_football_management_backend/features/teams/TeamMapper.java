package br.edu.ifpe.easy_football_management_backend.features.teams;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.domain.entity.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TeamMapper {

    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(source = "img", target = "urlImage")
    @Mapping(target = "user", expression = "java(findUserById(dto.userID()))")
    public abstract Team toEntity(TeamDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(source = "img", target = "urlImage")
    @Mapping(target = "user", expression = "java(findUserById(dto.userID()))")
    public abstract void updateTeamFromDto(TeamDTO dto, @MappingTarget Team team);

    protected User findUserById(java.util.UUID userId) {
        if (userId == null) {
            return null;
        }

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found."));
    }

    @Mapping(source = "urlImage", target = "img")
    @Mapping(source = "user.id", target = "userID")
    public abstract TeamDTO toDto(Team team);
}