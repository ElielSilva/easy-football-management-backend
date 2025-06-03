package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "statistics")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {
    @Id
    private UUID id;

    private Integer goals;
    private Integer yellowCards;
    private Integer redCards;
    private Integer participations;
    private Integer goalAgainst;
    private Integer matches;

    @ManyToOne
    @JoinColumn(name = "players_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "chanspions_ships_has_teams_id")
    private ChampionshipsTeams championshipTeam;

    @ManyToOne
    @JoinColumn(name = "results_id")
    private Result result;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }

    public Integer getParticipations() {
        return participations;
    }

    public void setParticipations(Integer participations) {
        this.participations = participations;
    }

    public Integer getRedCards() {
        return redCards;
    }

    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }

    public Integer getGoalAgainst() {
        return goalAgainst;
    }

    public void setGoalAgainst(Integer goalAgainst) {
        this.goalAgainst = goalAgainst;
    }

    public Integer getMatches() {
        return matches;
    }

    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ChampionshipsTeams getChampionshipTeam() {
        return championshipTeam;
    }

    public void setChampionshipTeam(ChampionshipsTeams championshipTeam) {
        this.championshipTeam = championshipTeam;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
