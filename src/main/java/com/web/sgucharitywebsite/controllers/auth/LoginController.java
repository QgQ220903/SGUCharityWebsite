package com.web.sgucharitywebsite.controllers.auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "auth/login"; // Trả về file login.html trong thư mục templates
    }
}