package com.company.isf.hr;

import com.company.isf.entity.User;
import com.company.isf.entity.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HrController {
    private final UserService userService;
    @Value("${hr.invite-code}")
    private String invite_code;

    public HrController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loghr")
    public String getLogin(){
        return "/hr/hrlogin";
    }

    @PostMapping("/loghr")
    public String postLogin(@RequestParam String code, HttpServletRequest httpServletRequest){
        if (!invite_code.equals(code)){
            return "redirect:/error";
        }

        httpServletRequest.getSession().setAttribute("HR_LOGGED_IN", true);
        return "redirect:/hr/dashboard";
    }

    @GetMapping("/hr/dashboard")
    public String dashboard(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/hr/dashboard";
    }

}
