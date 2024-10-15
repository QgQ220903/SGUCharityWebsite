package com.web.sgucharitywebsite.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminUserController {
    @RequestMapping("/admin/user")
    public String user() {
        return "admin/user/index";
    }

    @RequestMapping("/admin/user/add")
    public String add() {
        return "admin/user/add";
    }
}
