package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tournaments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "url_image")
    private String url_image;

    @Column(name= "modality")
    private MODALITY modality;

    @Column(name= "status")
    private Enum<STATUS> status;
    @Column(name= "created_at")
    private Date created_at;

    @Column(name= "quantity")
    private Integer quantity;

    @Column(name= "award")
    private Float award;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User userID;
}

