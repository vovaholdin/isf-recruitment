package com.company.isf.entity.client;

import com.company.isf.storageservice.FileStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ClientController {
    private final ClientService clientService;
    private final FileStorageService fileStorageService;

    public ClientController(ClientService clientService, FileStorageService fileStorageService) {
        this.clientService = clientService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/contact-client")
    public String showClientContactPage(Model model) {
        model.addAttribute("client", new Client());
        return "public/contact-client";
    }

    @PostMapping("/upload")
    public String uploadFile(@ModelAttribute Client client, @RequestParam("file")MultipartFile file){
        client.setMarked(false);
        clientService.save(client);
        if (!file.isEmpty()){
            fileStorageService.saveFilesClient(file, client);
        }
        return "redirect:/";
    }
}
