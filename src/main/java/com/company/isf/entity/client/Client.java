package com.company.isf.entity.client;

import com.company.isf.entity.files.files_client.FilesClient;
import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String company;
    private String email;
    private String phone;
    private String message;
    private boolean marked;
    @OneToMany(mappedBy = "client")
    List<FilesClient> filesClients = new ArrayList<>();

    public void addFile(FilesClient filesClient){
        filesClients.add(filesClient);
        filesClient.setClient(this);
    }

    public void removeFile(FilesClient filesClient){
        filesClients.remove(filesClient);
        filesClient.setClient(null);
    }

}
