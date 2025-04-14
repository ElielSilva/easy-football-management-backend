package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

//    @Column(name = "created_at", nullable = false)
//    private Date createdAt;
//
//    @Column(name = "updated_at")
//    private String updatedAt;
//
//    @Column(name = "deleted_at")
//    private Date deletedAt;
//
//    @PrePersist
//    protected void prePersist() {
//        if (this.createdAt == null) createdAt = new Date();
//    }
//
//    @PreUpdate
//    protected void preUpdate() {
//        this.deletedAt = new Date();
//    }
//
//    @PreRemove
//    protected void preRemove() {
//        this.deletedAt = new Date();
//    }
}
