package com.company.isf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    private Long id;
    private String name;
    private int experience;
    private String email;
    private int phonenumber;
    private String jobResponsibilities;
}
