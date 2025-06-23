package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "championship_id", nullable = false)
    private Championships championship;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @Column(name = "home_team_goals")
    private Integer homeTeamGoals;

    @Column(name = "away_team_goals")
    private Integer awayTeamGoals;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatus status;

    @Column(name = "match_date_time", nullable = false)
    private LocalDateTime matchDateTime;

    @Column(name = "round")
    private Integer round;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Statistic> statistics;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "next_match_id")
    private Match nextMatch;

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam) + Objects.hash(awayTeam);
    }

    public Team getWinner() {
        if (status == MatchStatus.COMPLETED) {
            if (homeTeamGoals != null && awayTeamGoals != null) {
                if (homeTeamGoals > awayTeamGoals) {
                    return homeTeam;
                } else if (awayTeamGoals > homeTeamGoals) {
                    return awayTeam;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Match match = (Match) obj;
        return (Objects.equals(homeTeam.getId(), match.homeTeam.getId()) &&
                Objects.equals(awayTeam.getId(), match.awayTeam.getId()));
    }
}
