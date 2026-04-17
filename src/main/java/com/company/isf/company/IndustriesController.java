package com.company.isf.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/industries")
public class IndustriesController {
    @GetMapping
    public String show(){
        return "services";
    }

    @GetMapping("/{type}")
    public String service(@PathVariable String type, Model model) {

        String title;
        String description;

        switch (type) {

            case "industrial-furnaces":
                title = "Industrial Furnaces";
                description = "Design, construction, and maintenance of industrial furnaces.";
                break;

            case "metal-structures":
                title = "Metal Structures";
                description = "Manufacturing of high-quality metal constructions for industrial use.";
                break;

            case "welding":
                title = "Welding Services";
                description = "Professional welding solutions and assembly services.";
                break;

            case "solar-panels":
                title = "Solar Panels";
                description = "Installation and maintenance of solar energy systems.";
                break;

            case "engineering":
                title = "Engineering Services";
                description = "Engineering and technical consulting for industrial projects.";
                break;

            default:
                return "error/404";
        }

        model.addAttribute("title", title);
        model.addAttribute("description", description);

        return "public/industries-sections";
    }
}
