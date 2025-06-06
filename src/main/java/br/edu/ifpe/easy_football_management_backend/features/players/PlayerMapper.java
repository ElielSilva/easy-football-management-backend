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
}