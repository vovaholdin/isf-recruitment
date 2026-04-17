package com.company.isf.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/services")
public class ServicesController {
    @GetMapping
    public String show(){
        return "public/services";
    }

    @GetMapping("/{type}")
    public String engineeringConsulting(@PathVariable String type, Model model) {
        String title;
        String description;

        switch (type) {

            case "industrial-furnaces-design":
                title = "Design and construction of industrial furnaces";
                description = "We design and build high-performance industrial furnaces tailored to your needs.";
                break;

            case "industrial-furnaces-repair":
                title = "Repair and maintenance of industrial furnaces";
                description = "We provide maintenance and repair services to ensure long-term performance.";
                break;

            case "metal-structures":
                title = "Manufacturing of metal structures";
                description = "We produce durable and reliable metal constructions for industrial use.";
                break;

            case "welding-assembly":
                title = "Welding and assembly services";
                description = "Professional welding and assembly solutions for complex projects.";
                break;

            case "engineering-consulting":
                title = "Engineering and technical consulting";
                description = "Expert consulting services for industrial and engineering challenges.";
                break;

            default:
                return "error/404";
        }

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        return "public/services-section";
    }
}
