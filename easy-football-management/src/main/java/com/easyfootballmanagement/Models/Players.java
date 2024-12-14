package com.easyfootballmanagement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Players {
    @Id
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Position position;
    private int number;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Teams teams;
}
