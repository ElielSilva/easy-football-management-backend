package br.edu.ifpe.easy_football_management_backend.features.tournament.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Tournament;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TournamentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TournamentQueryHandller {
    final TournamentRepository tournamentRepository;

    public TournamentQueryHandller(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> findAllTournament() {
        return tournamentRepository.findAll();
    }
}
