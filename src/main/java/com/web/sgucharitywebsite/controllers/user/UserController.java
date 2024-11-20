package com.web.sgucharitywebsite.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.repository.AppUserRepository;

import java.security.Principal;

@Controller
public class UserController {
  @Autowired
  private AppUserRepository appUserRepository;

  @GetMapping("/user")
  public String profile(Model model, Principal principal) {
    if (principal != null) {
      String email = principal.getName();
      AppUser appUser = appUserRepository.findByEmail(email);
      model.addAttribute("user", appUser);
    }
    return "project-detail";
  }

}
