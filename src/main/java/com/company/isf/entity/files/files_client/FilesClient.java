package com.company.isf.entity.files.files_client;

import com.company.isf.entity.client.Client;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "files_client")
public class FilesClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String url;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


}
