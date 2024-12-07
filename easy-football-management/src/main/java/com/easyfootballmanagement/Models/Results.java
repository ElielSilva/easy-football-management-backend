package com.easyfootballmanagement.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Results {
    @Id
    private long id;
    @OneToOne
    @JoinColumn(name = "home_id")
    private ChampionsShipsHasTeams home;
    private int qntGolsHome;
    @OneToOne
    @JoinColumn(name = "outside_id")
    private ChampionsShipsHasTeams outside;
    private int qntGolsOutside;
    private Date dateMatch;
}
