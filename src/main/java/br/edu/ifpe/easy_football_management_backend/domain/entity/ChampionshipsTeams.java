package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "championships_teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChampionshipsTeams {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;

    @ManyToOne
    @JoinColumn(name="team_id", nullable=false)
    private Team team;

    @ManyToOne
    @JoinColumn(name="championships_id", nullable=false)
    private Championships championships;
}
