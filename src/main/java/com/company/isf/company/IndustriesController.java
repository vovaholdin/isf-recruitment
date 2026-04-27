package com.company.isf.company;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/industries")
public class IndustriesController {

    private final MessageSource messageSource;

    public IndustriesController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping
    public String show(){
        return "public/industries";
    }

    @GetMapping("/{type}")
    public String service(@PathVariable String type, Model model, Locale locale) {
        model.addAttribute("industryKey", type);
        String raw = messageSource.getMessage("industries." + type + ".desc", null, locale);
        List<String> items = Arrays.stream(raw.split("\n"))
                .map(s -> s.replaceFirst("^\\s*-\\s*", "").trim())
                .filter(s -> !s.isEmpty())
                .toList();
        model.addAttribute("descItems", items);
        return "public/industries-sections";
    }
}
