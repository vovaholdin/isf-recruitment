package com.company.isf.entity.user;

import com.company.isf.entity.files.Files;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int experience;
    private String email;
    private String phone_number;
    private String jobResponsibilities;
    private String country;
    @Enumerated(EnumType.STRING)
    private Profession profession;
    private Boolean marked;
    @OneToMany(mappedBy = "user")
    List<Files> files;
}
