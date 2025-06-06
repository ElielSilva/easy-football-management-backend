package br.edu.ifpe.easy_football_management_backend.features.teams.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TeamQueryHandler {

    private final TeamRepository teamRepository;

    public TeamQueryHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * Retrieves all teams from the repository.
     *
     * @return a list of all teams.
     */
    @Transactional
    public List<Team> handler() {
        return teamRepository.findAll();
    }

    /**
     * Retrieves all teams from the repository.
     *
     * @return a teams.
     */
    @Transactional
    public Optional<Team> handler(UUID id) {
        return teamRepository.findById(id);
    }
}
