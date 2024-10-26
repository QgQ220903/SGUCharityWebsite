package com.web.sgucharitywebsite.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import com.web.sgucharitywebsite.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

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
