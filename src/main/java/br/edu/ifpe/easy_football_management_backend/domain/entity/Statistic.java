package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "statistics")
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
}
