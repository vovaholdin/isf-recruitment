package com.company.isf.entity.files.files_user;

import com.company.isf.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "files_user")
@NoArgsConstructor
@AllArgsConstructor
public class FilesUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String url;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
