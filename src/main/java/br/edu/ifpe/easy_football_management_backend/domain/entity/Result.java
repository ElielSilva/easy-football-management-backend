package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "results")
public class Result {
    @Id
    private UUID id;

    private UUID idHomeTeam;
    private Integer homeTeamGoals;
    private UUID idAwayTeam;
    private Integer awayTeamGoals;

    @ManyToOne
    @JoinColumn(name = "chamspionships_id")
    private Championships championship;

    @OneToMany(mappedBy = "result")
    private List<Statistic> statistics;
}
