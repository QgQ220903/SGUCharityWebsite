package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AdminArticleController {
    @Autowired
    private AppUserRepository appUserRepository;

    @RequestMapping("/admin/article")
    public String adminArticle(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }
        return "admin/article/index";
    }
    @RequestMapping("/admin/article/create")
    public String adminArticleCreate(Model model, Principal principal) {
            if (principal != null) {
                String email = principal.getName();
                AppUser appUser = appUserRepository.findByEmail(email);
                model.addAttribute("user", appUser);
            }
        return "admin/article/create";
    }
}
