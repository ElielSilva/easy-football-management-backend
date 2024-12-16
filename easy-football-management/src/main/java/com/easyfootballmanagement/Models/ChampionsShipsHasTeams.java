package com.easyfootballmanagement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChampionsShipsHasTeams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "champions_ships_id")
    private ChampionsShips championsShips;
    @OneToOne
    @JoinColumn(name = "teams_id")
    private Teams teams;
}
