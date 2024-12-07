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
public class ChampionsShipsHasTeams {
    @Id
    private long id;
    @OneToOne
    @JoinColumn(name = "champions_ships_id")
    private ChampionsShips championsShips;
    @OneToOne
    @JoinColumn(name = "teams_id")
    private Teams teams;
}
