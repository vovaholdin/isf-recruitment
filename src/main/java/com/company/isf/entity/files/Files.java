package com.company.isf.entity.files;

import com.company.isf.entity.user.User;
import jakarta.persistence.*;

@Entity
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String filename;
    String url;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
