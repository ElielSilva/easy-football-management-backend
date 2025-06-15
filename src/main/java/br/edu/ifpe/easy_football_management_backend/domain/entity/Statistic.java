package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "statistics")
@Data // Remove getters e setters manuais
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer goals = 0;
    @Column(nullable = false)
    private Integer yellowCards = 0;
    @Column(nullable = false)
    private Integer redCards = 0;
    @Column(nullable = false)
    private Integer assists = 0;
    @Column(name = "minutes_played")
    private Integer minutesPlayed = 0;

    @Column(name = "goals_conceded")
    private Integer goalsConceded = 0;
    @Column(name = "clean_sheets")
    private Integer cleanSheets = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "results_id")
    private Result result;

}