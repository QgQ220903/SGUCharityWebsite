package com.web.sgucharitywebsite.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminProjectController {
    @RequestMapping("/admin/project")
    public String index() {
        return "admin/project/index";
    }

    @RequestMapping("/admin/project/create")
    public String projectCreate() {
        return "admin/project/create";
    }
}
