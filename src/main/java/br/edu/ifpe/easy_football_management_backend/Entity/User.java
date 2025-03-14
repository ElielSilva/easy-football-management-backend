package br.edu.ifpe.easy_football_management_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "userId")
    private UUID userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
