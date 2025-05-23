package br.edu.ifpe.easy_football_management_backend.features.classification.command;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.BusinessException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import org.apache.coyote.BadRequestException;
import org.redisson.api.RedissonClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ClassificationHandler {

    private final ChampionshipsRepository championshipsRepository;
    private final ResultRepository resultRepository;
    private final RedissonClient redissonClient;

    public ClassificationHandler(
            ChampionshipsRepository championshipsRepository,
            ResultRepository resultRepository, RedissonClient redissonClient) {
        this.championshipsRepository = championshipsRepository;
        this.resultRepository = resultRepository;
        this.redissonClient = redissonClient;
    }

    @EventListener
    public void GenerateClassificationEvent(ChampionshipsEvent event) {
        String keyLock = "ClassificationHandler.GenerateClassificationEvent."+ event.getEventId()+"."+event.getChampionshipsId();
        var lock = redissonClient.getLock(keyLock);
        try {
            lock.lock();
            var championships = championshipsRepository.findById(event.getChampionshipsId())
                    .orElseThrow(() -> new BadRequestException("Championship not found"));
            var teams = championshipsRepository.findByChampionshipsContaining(championships);
            if (championships.getType().equals(TypeChampionship.CUP)) {
                generateRoundsCupAndSave(teams, championships);
            } else {
                generateRoundsLeagueAndSave(teams, championships);
            }
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private void generateRoundsLeagueAndSave(List<Team> teams, Championships championship) {
        int teamSize = teams.size();
        var allResults = new HashSet<Result>();
        for (int i = 0; i < teamSize; i++) {
            for (int j = i + 1; j < teamSize; j++){
                Team teamHome = teams.get(i);
                Team teamAway = teams.get(j);

                List<Statistic> statistics = new ArrayList<>();

                Result result = Result
                        .builder()
                        .idHomeTeam(teamHome.getId())
                        .homeTeamGoals(0)
                        .idAwayTeam(teamAway.getId())
                        .awayTeamGoals(0)
                        .championship(championship)
                        .statistics(statistics)
                        .build();
                allResults.add(result);

                Result returnResult = Result
                        .builder()
                        .idHomeTeam(teamAway.getId())
                        .homeTeamGoals(0)
                        .idAwayTeam(teamHome.getId())
                        .awayTeamGoals(0)
                        .championship(championship)
                        .statistics(statistics)
                        .build();
                allResults.add(returnResult);
            }
        }
        resultRepository.saveAll(allResults);
    }

   private void generateRoundsCupAndSave(List<Team> teams, Championships championship) {
       Collections.shuffle(teams);
       int teamSize = teams.size();

       if (teamSize % 2 != 0) {
           teams.add(null);
           throw new BusinessException("Cup not supported");
       }

       for (int i = 0; i < teamSize; i += 2) {
           Team teamHome = teams.get(i);
           Team teamAway = teams.get(i + 1);

           if (teamHome == null && teamAway == null) {
               throw new BusinessException("Team not supported in team");
           }

           if (teamHome == null || teamAway == null) {
               throw new BusinessException("Team not supported in team");
           }

           List<Statistic> statistics = new ArrayList<>();

           Result result = Result.builder()
                   .idHomeTeam(teamHome.getId())
                   .homeTeamGoals(0)
                   .idAwayTeam(teamAway.getId())
                   .awayTeamGoals(0)
                   .championship(championship)
                   .statistics(statistics)
                   .build();
           resultRepository.save(result);
       }
   }
}
