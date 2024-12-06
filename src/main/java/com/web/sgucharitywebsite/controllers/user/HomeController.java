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
import java.util.Map;
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
        // Kiểm tra nếu người dùng đã đăng nhập
        if (principal != null) {
            // Kiểm tra nếu người dùng đăng nhập qua OAuth2 (ví dụ: Google)
            if (principal instanceof org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken oauth2Token) {
                // Trích xuất thông tin email từ Google
                Map<String, Object> attributes = oauth2Token.getPrincipal().getAttributes();
                String email = (String) attributes.get("email");
                model.addAttribute("userEmail", email);
            } else {
                // Trường hợp người dùng đăng nhập qua form login
                String email = principal.getName();  // Đây là tên người dùng từ form login
                model.addAttribute("userEmail", email);
            }
        } else {
            // Nếu người dùng chưa đăng nhập, hiển thị là khách
            model.addAttribute("userEmail", "Guest");
        }

        List<CategoryDto> categoryDtoList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDtoList);
        return "homepage";
    }
}
