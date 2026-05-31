package com.company.isf.hr;


import com.company.isf.entity.client.Client;
import com.company.isf.entity.client.ClientService;
import com.company.isf.entity.files.files_client.FilesClient;
import com.company.isf.entity.user.User;
import com.company.isf.entity.user.UserService;
import com.company.isf.notification.MailSenderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.*;


import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HrController {
    private final UserService userService;
    private final MailSenderService senderService;
    private final ClientService clientService;

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
    public String sendMail(
                           @RequestParam String recipient,
                           @RequestParam String subject,
                           @RequestParam String message){

        senderService.sendEmail(recipient, subject, message);


        return "redirect:/hr/dashboard";
    }

    @PostMapping("/sendMailClient")
    public String sendMailClient(
            @RequestParam String recipient,
            @RequestParam String subject,
            @RequestParam String message){

        senderService.sendEmail(recipient, subject, message);


        return "redirect:/hr/dashboard";
    }

    @PatchMapping("/api/users/{id}/mark")
    public ResponseEntity<?> markUser(@PathVariable String id, @RequestBody Map<String, Boolean> body){
        userService.updateMarked(Long.parseLong(id), body.get("marked"));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/clients/{id}/mark")
    public ResponseEntity<?> markClient(@PathVariable String id, @RequestBody Map<String, Boolean> body){
        clientService.updateMarked(Long.parseLong(id), body.get("marked"));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hr/file-client/{id}")
    public ResponseEntity<Resource> showFile(@PathVariable Long id, Model model) throws MalformedURLException {
        List<FilesClient> filesClients = clientService.findById(id).orElseThrow().getFilesClients();
        FilesClient filesClient = filesClients.stream()
                .findFirst()
                .orElseThrow();
        Path path = Paths.get("uploads/" + filesClient.getFilename());
        org.springframework.core.io.Resource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=document.pdf")
                .body(resource);
    }

    @GetMapping("/hr/clients")
    public String showClient(Model model){
        List<Client> all = clientService.findAll();
        model.addAttribute("clients", all);
        return "hr/clients-dashboard";
    }

    @DeleteMapping("/hr/client/delete/{id}")
    public String deleteClient(@PathVariable Long id){
        clientService.delete(clientService.findById(id).orElseThrow());
        return "redirect:/hr/dashboard";
    }

    @GetMapping("/hr/client-detail/{id}")
    public String showClientDetail(@PathVariable Long id, Model model){
        Client client = clientService.findById(id).orElseThrow();
        model.addAttribute("client", client);
        return "hr/client";
    }


}
