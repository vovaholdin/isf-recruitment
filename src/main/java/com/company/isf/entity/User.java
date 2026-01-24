package com.company.isf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String name;
    private int experience;
    private String email;
    private String phone_number;
    private String jobResponsibilities;
    private String country;
    @Enumerated(EnumType.STRING)
    private Profession profession;
}
