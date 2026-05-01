package com.company.isf.company.services;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/services")
public class ServicesController {
    private final MessageSource messageSource;

    public ServicesController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping
    public String show(){
        return "public/services";
    }

    @GetMapping("/{type}")
    public String engineeringConsulting(@PathVariable String type, Model model, Locale locale) {
        model.addAttribute("serviceKey", type);
        String raw = messageSource.getMessage("services." + type + ".desc", null, locale);
        List<String> items = Arrays.stream(raw.split("\n"))
                .map(s -> s.replaceFirst("^\\s*-\\s*", "").trim())
                .filter(s -> !s.isEmpty())
                .toList();
        model.addAttribute("descItems", items);
        return "public/services-section";
    }
}
