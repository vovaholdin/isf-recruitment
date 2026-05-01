package com.company.isf.storageservice.impl;

import com.company.isf.entity.client.Client;
import com.company.isf.entity.client.ClientService;
import com.company.isf.entity.files.files_client.FilesClient;
import com.company.isf.entity.files.files_client.FilesClientService;
import com.company.isf.storageservice.FileStorageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Profile("local")
@Service
public class LocalStorageServiceImpl implements FileStorageService {
    private final FilesClientService filesClientService;


    public LocalStorageServiceImpl(FilesClientService filesClientService, ClientService clientService) {
        this.filesClientService = filesClientService;

    }

    @Override
    public String save(MultipartFile multipartFile, Client client) {
        try {
            String filename = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
            String uploadDir = "uploads/";
            Path path = Paths.get(uploadDir + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, multipartFile.getBytes());
            FilesClient build = FilesClient.builder()
                    .filename(filename)
                    .url(path.toString())
                    .build();
            client.addFile(build);
            filesClientService.save(build);


            return "/uploads/" + filename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void delete(String name, Client client) {
//        try {
//            client.getFilesClients().
//            Files.deleteIfExists(Paths.get(path));
//            client.removeFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
