package br.edu.ifpe.easy_football_management_backend.features.classification.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Match;
import br.edu.ifpe.easy_football_management_backend.domain.entity.MatchRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Matchs;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ClassificationQueryHandler {

    private final MatchRepository resultRepository;

    public ClassificationQueryHandler(MatchRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<Matchs> handlerMatches(UUID id) {
        var results = resultRepository.findByChampionshipId(id);

        List<Matchs> rounds = new ArrayList<>();
        var matchRounds = results.stream().collect(Collectors.groupingBy(Match::getRound));

       for (Integer round : matchRounds.keySet()) {
            List<Match> matches = matchRounds.get(round);
            Matchs matchs = new Matchs(round, matches);
            rounds.add(matchs);
        }

        return rounds;
    }

    public List<Match> handler(UUID id) {
        var results = resultRepository.findByChampionshipId(id);

        return results;
    }
}
