package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tournament_teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TournamentsTeams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="team_id", nullable=false)
    private User userID;

    @ManyToOne
    @JoinColumn(name="tournament_id", nullable=false)
    private Tournament tournament;
}
