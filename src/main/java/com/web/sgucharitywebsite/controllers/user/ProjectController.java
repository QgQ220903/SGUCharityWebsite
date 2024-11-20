package com.web.sgucharitywebsite.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.repository.AppUserRepository;
import java.security.Principal;

@Controller
public class ProjectController {
    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/project")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }
        return "project-list";
    }

    @GetMapping("/project/{id}")
    public String detail(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }
        return "project-detail";
    }

    @GetMapping("/project/detail/{id}")
    public String getMethodName(@PathVariable("id") long projectId, Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }
        return "project-detail";
    }

}
