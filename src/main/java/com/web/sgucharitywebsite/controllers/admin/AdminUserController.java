package com.web.sgucharitywebsite.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

  @GetMapping({ "", "/", "/index" })
  public String index() {
    return "admin/user/index";
  }

  @GetMapping("/create")
  public String create() {
    return "admin/user/create";
  }

}
