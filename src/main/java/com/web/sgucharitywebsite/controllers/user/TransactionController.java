package com.web.sgucharitywebsite.controllers.user;

import java.security.Principal;
import java.util.List;

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

  @Autowired
  public TransactionController(TransactionService transactionService, AppUserService appUserService) {
    this.transactionService = transactionService;
    this.appUserService = appUserService;
  }

  @GetMapping({"/transaction" })
  public String transaction(Model model, Principal principal) {
    if (principal != null) {
      String email = principal.getName();
      AppUser appUser = appUserService.findAppUserByEmail(email);
      List<Transaction> transactions = transactionService.findTranSactionByEmail(email);
      model.addAttribute("transactions", transactions);
      model.addAttribute("user", appUser);
    }
    return "transaction";
  }
}
