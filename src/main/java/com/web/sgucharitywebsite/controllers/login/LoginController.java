package com.web.sgucharitywebsite.controllers.login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Trả về file login.html trong thư mục templates
    }
}
