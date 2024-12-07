package com.easyfootballmanagement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teams {
    @Id
    private long id;
    private String name;
    @Column(name = "url_image")
    private String urlImg;
    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;
}
