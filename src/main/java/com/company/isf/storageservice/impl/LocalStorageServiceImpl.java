package com.company.isf.storageservice.impl;

import com.company.isf.storageservice.FileStorageService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class LocalStorageServiceImpl implements FileStorageService {
    private final String uploadDir = "uploads/";
    @Override
    public String save(MultipartFile multipartFile) {
        try {
        String filename = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        Path path = Paths.get(uploadDir + filename);
        Files.createDirectories(path.getParent());
        Files.write(path, multipartFile.getBytes());
        return "/uploads/" + filename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
