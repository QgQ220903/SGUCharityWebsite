package com.web.sgucharitywebsite.controllers.user;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.sgucharitywebsite.config.VNPAYService;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.entity.Transaction;
import com.web.sgucharitywebsite.repository.AppUserRepository;
import com.web.sgucharitywebsite.service.AppUserService;
import com.web.sgucharitywebsite.service.TransactionService;

@Controller
public class TransactionController {
  private TransactionService transactionService;
  private AppUserService appUserService;
  private CategoryService categoryService;
  @Autowired
  public TransactionController(TransactionService transactionService, AppUserService appUserService,CategoryService categoryService) {
    this.transactionService = transactionService;
    this.appUserService = appUserService;
    this.categoryService= categoryService;
  }

  @GetMapping({"/transaction" })
  public String transaction(Model model, Principal principal) {
//    if (principal != null) {
//      String email = principal.getName();
//      AppUser appUser = appUserService.findAppUserByEmail(email);
//      List<Transaction> transactions = transactionService.findTranSactionByEmail(email);
//      model.addAttribute("transactions", transactions);
//      model.addAttribute("userEmail", email);
//    }
    if (principal != null) {
      // Kiểm tra nếu người dùng đăng nhập qua OAuth2 (ví dụ: Google)
      if (principal instanceof org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken oauth2Token) {
        // Trích xuất thông tin email từ Google
        Map<String, Object> attributes = oauth2Token.getPrincipal().getAttributes();
        String email = (String) attributes.get("email");
        String emailTransaction = principal.getName();
        List<Transaction> transactions = transactionService.findTranSactionByEmail(emailTransaction);
        model.addAttribute("transactions", transactions);
        model.addAttribute("userEmail", email);
      } else {
        // Trường hợp người dùng đăng nhập qua form login
        String email = principal.getName();  // Đây là tên người dùng từ form login
        List<Transaction> transactions = transactionService.findTranSactionByEmail(email);
        model.addAttribute("transactions", transactions);
        model.addAttribute("userEmail", email);
      }
    } else {
      // Nếu người dùng chưa đăng nhập, hiển thị là khách
      model.addAttribute("userEmail", "Guest");
    }
    List<CategoryDto> categoryDtoList = categoryService.findAllCategories();
    model.addAttribute("categories", categoryDtoList);
    return "transaction";
  }
}
