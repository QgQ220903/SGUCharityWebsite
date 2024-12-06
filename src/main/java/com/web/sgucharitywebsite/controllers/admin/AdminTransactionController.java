package com.web.sgucharitywebsite.controllers.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.sgucharitywebsite.dto.RegistrationDto;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.entity.Transaction;
import com.web.sgucharitywebsite.service.AppUserService;
import com.web.sgucharitywebsite.service.TransactionService;

@Controller
@RequestMapping("/admin")
public class AdminTransactionController {
  private TransactionService transactionService;
  private AppUserService appUserService;

  @Autowired
  public AdminTransactionController(TransactionService transactionService, AppUserService appUserService) {
    this.transactionService = transactionService;
    this.appUserService = appUserService;
  }

  @GetMapping({ "", "/transaction", "/transaction/index" })
  public String index(Model model, Principal principal) {
    if (principal != null) {
      String email = principal.getName();
      AppUser appUser = appUserService.findAppUserByEmail(email);
      model.addAttribute("user", appUser);
    }
    List<Transaction> transactions = transactionService.findByOrderByVnpPayDateDesc();
    model.addAttribute("transactions", transactions);
    return "admin/transaction/index";
  }

}
