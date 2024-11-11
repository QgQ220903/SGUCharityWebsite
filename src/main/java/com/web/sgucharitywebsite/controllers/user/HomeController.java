package com.web.sgucharitywebsite.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("")
    public String home ()
    {
        return "user/home/index";
    }
}
