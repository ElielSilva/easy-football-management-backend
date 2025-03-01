package com.easyfootballmanagement.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int gols;
    private int qntYellowCards;
    private int qntRedCards;
    private int participations;
    private int qntGolsAgainst;
    private int matchs;
    @OneToOne
    @JoinColumn(name = "players_id")
    private Players players;
    @OneToOne
    @JoinColumn(name = "teams_id")
    private ChampionsShipsHasTeams teams;
    @OneToOne
    @JoinColumn(name = "results_id")
    private Results results;
}
