package com.company.isf.entity.files.files_user;

import com.company.isf.entity.files.files_client.FilesClient;
import com.company.isf.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FilesUserRepository extends JpaRepository<FilesUser, Long> {
    @Query("SELECT f from FilesUser f WHERE f.filename = :filename")
    FilesUser findByName(String filename);

    @Modifying
    @Query("delete from FilesUser f where f.filename = :filename")
    void deleteByName(String filename);
}
