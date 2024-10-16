package com.web.sgucharitywebsite.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminArticleController {
    @RequestMapping("/admin/article")
    public String adminArticle() {
        return "admin/article/index";
    }
    @RequestMapping("/admin/article/create")
    public String adminArticleCreate() {
        return "admin/article/create";
    }
}
