package com.web.sgucharitywebsite.controllers.auth;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import org.springframework.ui.Model;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "auth/login"; // Trả về file login.html trong thư mục templates
    }
}