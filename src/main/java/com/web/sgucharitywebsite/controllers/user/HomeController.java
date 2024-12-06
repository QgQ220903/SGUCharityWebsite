package com.web.sgucharitywebsite.controllers.user;

import com.web.sgucharitywebsite.config.VNPAYService;
import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.repository.AppUserRepository;

import com.web.sgucharitywebsite.repository.CategoryRepository;
import com.web.sgucharitywebsite.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private AppUserRepository appUserRepository;
    private VNPAYService vnPayService;
    private CategoryService categoryService;
    @Autowired
    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping({ "/", "/home" })
    public String home(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }
        List<CategoryDto> categoryDtoList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDtoList);
        return "homepage";
    }
}
