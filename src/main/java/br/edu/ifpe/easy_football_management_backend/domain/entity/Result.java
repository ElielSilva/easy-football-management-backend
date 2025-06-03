package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "results")
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID idHomeTeam;
    private Integer homeTeamGoals;
    private UUID idAwayTeam;
    private Integer awayTeamGoals;

    @Enumerated(EnumType.STRING)
    private STATUS status;

    @ManyToOne
    @JoinColumn(name = "chamspionships_id")
    private Championships championship;

    @OneToMany(mappedBy = "result")
    private List<Statistic> statistics;

    @Override
    public int hashCode() {
        return Objects.hash(idHomeTeam) + Objects.hash(idAwayTeam);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Result result = (Result) obj;
        return (Objects.equals(idHomeTeam, result.idHomeTeam) &&
                Objects.equals(idAwayTeam, result.idAwayTeam));
    }
}
