package com.company.isf.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @GetMapping
    public String showContact(){
        return "public/contact";
    }

    //todo доделать эту страницу что бы она мапила и добавить фото
}
