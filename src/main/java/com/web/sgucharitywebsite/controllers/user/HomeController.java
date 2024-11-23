package com.web.sgucharitywebsite.controllers.user;

import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping({ "/", "/home" })
    public String home(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }
        return "homepage";
    }
}
