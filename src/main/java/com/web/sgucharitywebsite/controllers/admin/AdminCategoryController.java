package com.web.sgucharitywebsite.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminCategoryController {
    @RequestMapping("/admin/category")
    public String adminCategory() {
        return "admin/category/index";
    }

    @RequestMapping("/admin/category/create")
    public String adminCategoryCreate() {
        return "admin/category/create";
    }
}
