package com.company.isf.vacancy;

import com.company.isf.entity.Profession;
import com.company.isf.entity.User;
import com.company.isf.entity.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VacancyController {
    private final UserService userService;

    public VacancyController(UserService userService) {
        this.userService = userService;
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
    public String submitVacancy(@ModelAttribute("application") User user){
        userService.create(user);
        return "redirect:/";

    }
}
