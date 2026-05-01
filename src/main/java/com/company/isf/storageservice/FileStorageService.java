package com.company.isf.storageservice;

import com.company.isf.entity.client.Client;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String save (MultipartFile multipartFile, Client client);
//    void delete(String path, Client client);
}
