package com.company.isf.entity.files.files_client;

import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FilesClientService {
    private final FilesClientRepository repository;

    public FilesClientService(FilesClientRepository repository) {
        this.repository = repository;
    }

    public FilesClient findById(Long id){
        return repository.findById(id).orElseThrow();

    }

    public FilesClient findByName(String fileName){
        return repository.findByName(fileName);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void deleteByName(String filename){
        repository.deleteByName(filename);
    }

    public void save(FilesClient filesClient){
        repository.save(filesClient);
    }
}
