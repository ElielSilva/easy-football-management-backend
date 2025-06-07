package br.edu.ifpe.easy_football_management_backend.features.teams.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import br.edu.ifpe.easy_football_management_backend.features.teams.TeamDtoResponse;
import br.edu.ifpe.easy_football_management_backend.features.teams.TeamMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TeamQueryHandler {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamQueryHandler(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    /**
     * Retrieves all teams from the repository.
     *
     * @return a list of all teams.
     */
    @Transactional
    public List<TeamDtoResponse> handler() {
        var x = teamRepository.findAll().stream();
        var res = x.map(teamMapper::toDtoResponse).toList();
        return res;
    }

    /**
     * Retrieves all teams from the repository.
     *
     * @return a teams.
     */
    @Transactional
    public Optional<TeamDtoResponse> handler(UUID id) {
        var x = teamRepository.findById(id);
        return x.map(teamMapper::toDtoResponse);
    }
}
