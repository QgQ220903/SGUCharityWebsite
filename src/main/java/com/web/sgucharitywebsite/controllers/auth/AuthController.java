package com.web.sgucharitywebsite.controllers.auth;

import com.web.sgucharitywebsite.dto.RegistrationDto;
import com.web.sgucharitywebsite.entity.User;
import com.web.sgucharitywebsite.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
   private UserService userService;
   public AuthController(UserService userService) {
       this.userService = userService;
   }
   @GetMapping("auth/register")
    public String register(Model model) {
       RegistrationDto user = new RegistrationDto();
       model.addAttribute("user", user);
       return "auth/register";
   }
    @PostMapping("auth/register/save")
    public String saveRegister(@Valid RegistrationDto user,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        User existingUser = userService.findUserByEmail(user.getEmail());
        if (existingUser != null) {
            bindingResult.rejectValue("email", "Da co tai khoan su dung email nay");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user); // Truyền lại dữ liệu user để hiển thị lại trên form
            return "auth/register";
        }
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/user";
    }
}
