package br.edu.ifpe.easy_football_management_backend.features.teams.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.teams.TeamDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeamCommandHandler {
    private final TeamRepository teamRepository;

    public TeamCommandHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team handler(TeamDTO team) {
        isValid(team);
        Team teamEntity = this.map(team);
        return teamRepository.save(teamEntity);
    }

    @Transactional
    public Team handler(TeamDTO team, UUID teamId) {
        if (teamId != null) {
            if (!isExist(teamId)){
                throw new IllegalArgumentException("Team ID " + teamId + " does not exist");
            }
        }
        isValid(team);
        Team teamEntity = this.map(team);
        teamEntity.setId(teamId);
        teamRepository.save(teamEntity);
        return teamEntity;
    }

    @Transactional
    public void handler(UUID teamId) {
        if (teamId != null) {
            if (!isExist(teamId)){
                throw new IllegalArgumentException("Team ID " + teamId + " does not exist");
            }
            teamRepository.deleteById(teamId);
        }
    }

    private boolean isExist(UUID teamId) {
        return teamRepository.existsById(teamId);
    }


    private Team map(TeamDTO team) {
        Team teamEntity = new Team();
        teamEntity.setName(team.name());
        teamEntity.setUrlImage(team.img());
        teamEntity.setDeleted(false);
        if (team.userID() != null) {
            teamEntity.setUser(User.builder().id(team.userID()).build());
        }
        return teamEntity;
    }

    private void isValid(TeamDTO team) {
        if (team.name() == null || team.name().isEmpty() || team.name().length() < 3 || team.name().length() > 100) {
            throw new IllegalArgumentException();
        }
        if (team.img() == null || team.img().isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
