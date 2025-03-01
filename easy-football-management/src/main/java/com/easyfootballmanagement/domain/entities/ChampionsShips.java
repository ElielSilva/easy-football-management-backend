package com.easyfootballmanagement.domain.entities;

import com.easyfootballmanagement.domain.enums.Status;
import com.easyfootballmanagement.domain.enums.TypeChampionsShips;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChampionsShips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TypeChampionsShips type;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "created_at")
    private Date createdAt;
    private float award;
    private int quantity;
    @OneToOne
    private Users users;
}
