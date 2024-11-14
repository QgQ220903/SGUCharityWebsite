package com.web.sgucharitywebsite.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class HomeController {
    @GetMapping({"", "/home", "/home/index"})
    public String home ()
    {
        return "user/home/index";
    }
}
