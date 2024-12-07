package com.easyfootballmanagement.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {
    @Id
    private long id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String phone;
    @Column(name = "url_image")
    private String urlImg;
}
