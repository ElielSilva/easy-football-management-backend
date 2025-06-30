package br.edu.ifpe.easy_football_management_backend.features.classification.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ClassificationQueryHandler {

    private final MatchRepository resultRepository;
    private final StandingRepository standingRepository;

    public ClassificationQueryHandler(MatchRepository resultRepository, StandingRepository standingRepository) {
        this.resultRepository = resultRepository;
        this.standingRepository = standingRepository;
    }


    public List<Matchs> handlerMatches(UUID id) {
        var results = resultRepository.findByChampionshipId(id);

//        if(results.getFirst().getChampionship().getType() == TypeChampionship.CUP) {
//
//        }
        List<Matchs> rounds = new ArrayList<>();
        var matchRounds = results.stream().collect(Collectors.groupingBy(Match::getRound));

       for (Integer round : matchRounds.keySet()) {
            List<Match> matches = matchRounds.get(round);
            Matchs matchs = new Matchs(round, matches);
            rounds.add(matchs);
        }

        return rounds;
    }

    public List<Standing> handler(UUID id) {
        var results = standingRepository.findByChampionshipIdOrderByPointsDescGoalsForDesc(id);

        return results;
    }
}
