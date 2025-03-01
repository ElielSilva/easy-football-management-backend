package com.easyfootballmanagement.domain.entities;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_id")
    private ChampionsShipsHasTeams home;
    private int qntGolsHome;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "outside_id")
    private ChampionsShipsHasTeams outside;
    private int qntGolsOutside;
    private Date dateMatch;
}
