package com.easyfootballmanagement.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Statistics {
    @Id
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
