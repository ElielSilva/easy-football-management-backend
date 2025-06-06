package br.edu.ifpe.easy_football_management_backend.features.teams.command;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.NotFoundException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import br.edu.ifpe.easy_football_management_backend.features.teams.TeamDTO;
import br.edu.ifpe.easy_football_management_backend.features.teams.TeamMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeamCommandHandler {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamCommandHandler(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public Team handler(TeamDTO team) {
        Team teamEntity = teamMapper.toEntity(team);
        return teamRepository.save(teamEntity);
    }

    @Transactional
    public Team handler(TeamDTO team, UUID teamId) {
        if (teamId != null) {
            if (isNotExist(teamId)) {
                throw new NotFoundException("Team ID " + teamId + " does not exist");
            }
        }
        Team teamEntity = teamMapper.toEntity(team);
        teamEntity.setId(teamId);
        teamRepository.save(teamEntity);
        return teamEntity;
    }

    @Transactional
    public void handler(UUID teamId) {
        if (teamId != null) {
            if (isNotExist(teamId)) {
                throw new NotFoundException("Team ID " + teamId + " does not exist");
            }
            teamRepository.deleteById(teamId);
        }
    }

    private boolean isNotExist(UUID teamId) {
        return teamRepository.existsById(teamId);
    }
}
