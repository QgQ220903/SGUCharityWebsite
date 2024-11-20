package com.web.sgucharitywebsite.controllers.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.dto.RegistrationDto;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.repository.AppUserRepository;
import com.web.sgucharitywebsite.service.AppUserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

  private AppUserService appUserService;

  @Autowired
  public AdminUserController(AppUserService appUserService) {
    this.appUserService = appUserService;
  }

  @GetMapping({ "", "/user", "/user/index" })
  public String index(Model model) {
    List<RegistrationDto> appUserList = appUserService.findAllAppUser();
    model.addAttribute("appUsers", appUserList);
    return "admin/user/index";
  }

  @GetMapping("/user/create")
  public String create(Model model) {
    RegistrationDto registrationDto = new RegistrationDto();
    model.addAttribute("registrationDto", registrationDto);
    return "admin/user/create";
  }

  @PostMapping("/user/create")
  public String create(Model model,
      @Valid @ModelAttribute RegistrationDto registrationDto,
      BindingResult result) {
    AppUser existingUser = appUserService.findAppUserByEmail(registrationDto.getEmail());
    if (existingUser != null) {
      result.addError(new FieldError("registrationDto", "email", "Email đã có người sử dụng"));
    }
    if (result.hasErrors()) {
      return "admin/user/create";
    }
    try {
      var bcryptEncoder = new BCryptPasswordEncoder();
      registrationDto.setPassword(bcryptEncoder.encode(registrationDto.getPassword()));
      appUserService.saveAppUser(registrationDto);
      model.addAttribute("registrationDto", new RegistrationDto());
      model.addAttribute("success", true);
    } catch (Exception e) {
      result.addError(new FieldError("registrationDto", "fullName", e.getMessage()));
    }
    return "admin/user/index";
  }

}
