package com.company.isf.entity.files.files_client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FilesClientRepository extends JpaRepository<FilesClient, Long> {
    @Query("SELECT f from FilesClient f WHERE f.filename = :filename")
    FilesClient findByName(String filename);

    @Modifying
    @Query("delete from FilesClient f where f.filename = :filename")
    void deleteByName(String filename);
}
