package br.edu.ifpe.easy_football_management_backend.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "timeId", nullable = false)
    private Team time;
}
