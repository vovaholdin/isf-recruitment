package com.company.isf.storageservice;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String save (MultipartFile multipartFile);
    void delete(String path);
}
