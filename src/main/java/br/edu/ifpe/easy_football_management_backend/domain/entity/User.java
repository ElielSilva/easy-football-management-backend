package br.edu.ifpe.easy_football_management_backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Team> teams;
}
