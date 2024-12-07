package com.easyfootballmanagement.Models;

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
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;
}
