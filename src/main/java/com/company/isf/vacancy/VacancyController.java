package com.company.isf.vacancy;

import com.company.isf.entity.files.files_user.FilesUserService;
import com.company.isf.entity.user.Profession;
import com.company.isf.entity.user.User;
import com.company.isf.entity.user.UserService;
import com.company.isf.storageservice.FileStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class VacancyController {
    private final UserService userService;
    private final FileStorageService fileStorageService;

    public VacancyController(UserService userService, FilesUserService filesUserService, FileStorageService fileStorageService) {
        this.userService = userService;
        this.fileStorageService = fileStorageService;

    }

    @GetMapping("/vacancies")
    public String showForm(Model model) {

        return "public/vacancies";
    }

    @GetMapping("/vacancy")
    public String vacancyType(@RequestParam("type") String type, Model model) {
        Profession profession = switch (type){
            case "welder" -> Profession.WELDER;
            case "locksmith" -> Profession.LOCKSMITH;
            case "beginner" -> Profession.BEGINNER;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        User application = new User();
        application.setProfession(profession);
        model.addAttribute("application", application);

        return switch (type) {
            case "welder" -> "public/vacancy-welder";
            case "locksmith" -> "public/vacancy-locksmith";
            case "beginner" -> "public/vacancy-beginner";
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    @PostMapping("/apply")
    public String submitVacancy(@ModelAttribute("application") User user, @RequestParam("cv") MultipartFile file){
        user.setMarked(false);
        userService.create(user);
        if (!file.isEmpty()){
            fileStorageService.saveFilesUser(file, user);
        }
        return "redirect:/";

    }
}
