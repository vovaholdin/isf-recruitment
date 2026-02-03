package com.company.isf.hr;

import com.company.isf.entity.User;
import com.company.isf.entity.UserService;
import com.company.isf.notification.MailSenderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class HrController {
    private final UserService userService;
    private final MailSenderService senderService;

    @Value("${hr.invite-code}")
    private String invite_code;



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

    @GetMapping("/hr/user/delete/{id}")
    public String delete(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/hr/dashboard";
    }

    @GetMapping("/hr/user/{id}")
    public String viewInfo(@PathVariable Long id, Model model){
        User user = userService.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "/hr/user";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam Long userId,
                           @RequestParam String recipient,
                           @RequestParam String subject,
                           @RequestParam String message,
                           RedirectAttributes redirectAttributes){

        senderService.sendEmail(recipient, subject, message);

        return "redirect:/hr/dashboard";
    }


}
