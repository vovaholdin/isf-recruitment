package com.company.isf.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    @GetMapping("/contact-client")
    public String showClientContactPage(Model model) {
        model.addAttribute("client", new Client());
        return "public/contact-client";
    }
}
