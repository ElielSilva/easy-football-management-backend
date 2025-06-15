package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "standings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Standing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "championship_id", nullable = false)
    private Championships championship; // Renomeado para Championship

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(nullable = false)
    private int points = 0;
    @Column(nullable = false)
    private int wins = 0;
    @Column(nullable = false)
    private int draws = 0;
    @Column(nullable = false)
    private int losses = 0;
    @Column(name = "goals_for", nullable = false)
    private int goalsFor = 0;
    @Column(name = "goals_against", nullable = false)
    private int goalsAgainst = 0;
    @Column(name = "goal_difference", nullable = false)
    private int goalDifference = 0;

    @Column(name = "games_played", nullable = false)
    private int gamesPlayed = 0;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}
