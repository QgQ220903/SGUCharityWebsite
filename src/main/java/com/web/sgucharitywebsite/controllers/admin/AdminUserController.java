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
import com.web.sgucharitywebsite.dto.RegistrationDto;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
      model.addAttribute("success", true);
      return "redirect:/admin/user"; // Redirect to success page
    } catch (Exception e) {
      result.addError(new FieldError("registrationDto", "fullName", e.getMessage()));
      return "admin/user/create"; // Return to create page with error
    }
  }

  @GetMapping("/user/delete/{userId}")
  public String delete(@PathVariable("userId") long userId, Model model) {
    appUserService.deleteAppUserById(userId);
    model.addAttribute("message", "Xóa người dùng thành công!");
    return "redirect:/admin/user";
  }

  @GetMapping("/user/update/{userId}")
  public String update(@PathVariable("userId") long userId, Model model) {
    RegistrationDto registrationDto = appUserService.findAppUserById(userId);
    model.addAttribute("registrationDto", registrationDto);
    return "admin/user/update";
  }

  @GetMapping("/user/detail/{userId}")
  public String detail(@PathVariable("userId") long userId, Model model) {
    RegistrationDto registrationDto = appUserService.findAppUserById(userId);
    model.addAttribute("registrationDto", registrationDto);
    return "admin/user/detail";
  }

  @PostMapping("/user/update/{userId}")
  public String update(@PathVariable("userId") long userId,
      @Valid @ModelAttribute("registrationDto") RegistrationDto registrationDto,
      BindingResult result) {
    if (result.hasErrors()) {
      return "admin/user/update";
    }
    // Kiểm tra trùng lặp email
    AppUser existingUser = appUserService.findAppUserByEmail(registrationDto.getEmail());
    if (existingUser != null && existingUser.getId() != userId) {
      result.addError(new FieldError("registrationDto", "email", "Email đã tồn tại"));
      return "admin/user/update";
    }
    registrationDto.setId(userId);
    
    appUserService.updateAppUser(registrationDto);
    return "redirect:/admin/user";
  }

}
