package br.edu.ifpe.easy_football_management_backend.features.results;

import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsTeams;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Statistic;

import java.util.List;
import java.util.UUID;

public class ResultMapper {

    public static List<Statistic> mapToResult(ResultDTO result) {
        return result.playersResults.stream().map(x ->
                Statistic
                        .builder()
                        .player(
                                Player
                                        .builder()
                                        .id(x.playerId)
                                        .build())
                        .goals(x.goals)
                        .redCards(x.redCards)
                        .yellowCards(x.yellowCards)
                        .goalAgainst(x.goalAgainst)
                        .participations(x.participations)
                        .build()
        ).toList();
    }
}
