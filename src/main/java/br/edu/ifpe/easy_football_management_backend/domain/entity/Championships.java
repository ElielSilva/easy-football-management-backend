package br.edu.ifpe.easy_football_management_backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    @Size(min = 0, max = 500, message = "A descrição deve ter entre 0 e 500 caracteres.")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeChampionship type;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal award;

    @Column(name = "quantity_teams")
    private Integer quantityTeams;

    @Enumerated(EnumType.STRING)
    private StatusChampionship status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "championship", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonBackReference
    private List<ChampionshipsTeams> registeredTeams; // Relacionamento com times inscritos

    @OneToMany(mappedBy = "championship", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    private List<Match> matchs;
}

