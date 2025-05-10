package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "results")
public class Result {
    @Id
    private UUID id;

    private Integer idHomeTeam;
    private Integer homeTeamGoals;
    private Integer idAwayTeam;
    private Integer awayTeamGoals;

    @ManyToOne
    @JoinColumn(name = "chanspions_ships_has_teams_id")
    private ChampionshipsTeams championshipTeam;

    @OneToMany(mappedBy = "result")
    private List<Statistic> statistics;
}
