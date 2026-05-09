package com.company.isf.storageservice;

import com.company.isf.entity.client.Client;
import com.company.isf.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String saveFilesClient (MultipartFile multipartFile, Client client);
    String saveFilesUser (MultipartFile multipartFile, User user);
//    void delete(String path, Client client);
}
