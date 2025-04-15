package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "championships")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Championships {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeChampionship type;
    @Builder.Default
    private Date createdAt = new Date();
    private float award;
    @Column(name = "qnt_teams")
    private int quantityTeams;
    @Enumerated(EnumType.STRING)
    private StatusChampionship status;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
}

