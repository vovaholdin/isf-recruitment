package com.company.isf.storageservice.impl;

import com.company.isf.entity.client.Client;
import com.company.isf.entity.client.ClientService;
import com.company.isf.entity.files.files_client.FilesClient;
import com.company.isf.entity.files.files_client.FilesClientService;
import com.company.isf.entity.files.files_user.FilesUser;
import com.company.isf.entity.files.files_user.FilesUserService;
import com.company.isf.entity.user.User;
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
    private final FilesUserService filesUserService;


    public LocalStorageServiceImpl(FilesClientService filesClientService, ClientService clientService, FilesUserService filesUserService) {
        this.filesClientService = filesClientService;

        this.filesUserService = filesUserService;
    }

    @Override
    public String saveFilesClient(MultipartFile multipartFile, Client client) {
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


    @Override
    public String saveFilesUser(MultipartFile multipartFile, User user) {
        try {
            String filename = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
            String uploadDir = "uploads/";
            Path path = Paths.get(uploadDir + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, multipartFile.getBytes());
            FilesUser build = FilesUser.builder()
                    .filename(filename)
                    .url(path.toString())
                    .build();
            user.addFile(build);
            filesUserService.save(build);


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
