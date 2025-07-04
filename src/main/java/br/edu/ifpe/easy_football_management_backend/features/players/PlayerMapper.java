package br.edu.ifpe.easy_football_management_backend.features.players;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PlayerMapper {

    @Autowired
    protected TeamRepository teamRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", expression = "java(findTeamById(dto.teamId()))")
    public abstract Player toEntity(PlayerDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", expression = "java(findTeamById(dto.teamId()))")
    public abstract void updatePlayerFromDto(PlayerDTO dto, @MappingTarget Player player);

    protected Team findTeamById(UUID teamId) {
        if (teamId == null) {
            return null;
        }

        return teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team with ID " + teamId + " not found."));
    }

    @Mapping(source = "team.id", target = "teamId")
    public abstract PlayerDTO toDto(Player player);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "position", source = "position")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "team", source = "team") // MapStruct vai usar o TeamMapper para este campo
    public abstract PlayerDtoResponse toDtoResponse(Player player);

    // Opcional: Mapping de DTO para Entity
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "position", source = "position")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "team", source = "team")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Player toEntity(PlayerDtoResponse playerDtoResponse);
}