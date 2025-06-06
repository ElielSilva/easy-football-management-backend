package br.edu.ifpe.easy_football_management_backend.features.classification.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Matchs;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Result;
import br.edu.ifpe.easy_football_management_backend.domain.entity.ResultRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ClassificationQueryHandler {

    private final ResultRepository resultRepository;

    public ClassificationQueryHandler(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<Matchs> handlerMatches(UUID id) {
        var results = resultRepository.findByChampionshipId(id);

        long teamsCount = results.stream()
                .flatMap(r -> List.of(r.getIdHomeTeam(), r.getIdAwayTeam()).stream())
                .distinct()
                .count();

        int totalRounds = (int) (teamsCount - 1);

        List<Matchs> rounds = new ArrayList<>();
        for (int i = 1; i <= totalRounds; i++) {
            int roundNumber = i;
            int gamesPerRound = (int) Math.ceil(results.size() / (double) totalRounds);
            int startIndex = (i - 1) * gamesPerRound;
            int endIndex = Math.min(startIndex + gamesPerRound, results.size());

            if (startIndex < results.size()) {
                List<Result> roundGames = new ArrayList<>(
                        results.subList(startIndex, endIndex)
                );

                Matchs match = new Matchs(roundNumber, roundGames);
                rounds.add(match);
            }
        }

        return rounds;
    }

    public List<Result> handler(UUID id) {
        var results = resultRepository.findByChampionshipId(id);

        return results;
    }
}
