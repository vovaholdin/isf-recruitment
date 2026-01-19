package com.company.isf.vacancy;

import com.company.isf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VacancyController {

    @GetMapping("/vacancies")
    public String showForm(Model model) {

        return "public/vacancies";
    }

    @GetMapping("/vacancy")
    public String vacancyType(@RequestParam("type") String type, Model model) {
        model.addAttribute("application", new User());
        return switch (type) {
            case "welder" -> "public/vacancy-welder";
            case "locksmith" -> "public/vacancy-locksmith";
            case "beginner" -> "public/vacancy-beginner";
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
    //todo починить маппинг с локалью, стирается мой параметр добавляется параметр маппинга
}
