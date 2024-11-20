package com.web.sgucharitywebsite.controllers.auth;

import java.sql.Date;

import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.web.sgucharitywebsite.dto.RegistrationDto;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.repository.AppUserRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AccountController {
  @Autowired
  private AppUserRepository appUserRepository;

  @GetMapping("/register")
  public String register(Model model) {
    RegistrationDto registrationDto = new RegistrationDto();
    model.addAttribute(registrationDto);
    model.addAttribute("success", false);
    return "auth/register";
  }

  @PostMapping("/register")
  public String register(
      Model model,
      @Valid @ModelAttribute RegistrationDto registrationDto,
      BindingResult result) {
    if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
      result.addError(new FieldError("registrationDto", "confirmPassword", "Mật khẩu không khớp"));
    }
    AppUser appUser = appUserRepository.findByEmail(registrationDto.getEmail());
    if (appUser != null) {
      result.addError(new FieldError("registrationDto", "email", "Email đã có người sử dụng"));
    }
    if (result.hasErrors()) {
      return "auth/register";
    }
    try {
      var bcryptEncoder = new BCryptPasswordEncoder();
      AppUser newUser = new AppUser();
      newUser.setFullName(registrationDto.getFullName());
      newUser.setEmail(registrationDto.getEmail());
      newUser.setPhone(registrationDto.getPhone());
      newUser.setRole("USER");
      newUser.setCreateOn(registrationDto.getCreateOn());
      newUser.setPassword(bcryptEncoder.encode(registrationDto.getPassword()));
      appUserRepository.save(newUser);
      model.addAttribute("registrationDto", new RegistrationDto());
      model.addAttribute("success", true);
    } catch (Exception e) {
      result.addError(new FieldError("registrationDto", "fullName", e.getMessage()));
    }

    return "auth/register";
  }

}
