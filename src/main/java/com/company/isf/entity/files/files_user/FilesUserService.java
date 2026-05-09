package com.company.isf.entity.files.files_user;

import com.company.isf.entity.files.files_client.FilesClient;
import org.springframework.stereotype.Service;

@Service
public class FilesUserService {
    private final FilesUserRepository repository;

    public FilesUserService(FilesUserRepository repository) {
        this.repository = repository;
    }

    public FilesUser findById(Long id){
        return repository.findById(id).orElseThrow();

    }

    public FilesUser findByName(String fileName){
        return repository.findByName(fileName);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void deleteByName(String filename){
        repository.deleteByName(filename);
    }

    public void save(FilesUser filesUser){
        repository.save(filesUser);
    }
}
