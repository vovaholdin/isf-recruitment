package com.company.isf.entity.user;

import com.company.isf.entity.files.files_user.FilesUser;
import com.company.isf.entity.files.files_user.FilesUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final FilesUserService filesUserService;
    private final UserRepository userRepository;

    public UserService(FilesUserService filesUserService, UserRepository userRepository) {
        this.filesUserService = filesUserService;
        this.userRepository = userRepository;
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    public void updateMarked(Long id, boolean isMark){
        User user = userRepository.findById(id).orElseThrow();
        user.setMarked(isMark);
        userRepository.save(user);
    }
}
