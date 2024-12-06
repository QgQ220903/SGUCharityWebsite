package com.web.sgucharitywebsite.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.sgucharitywebsite.dto.RegistrationDto;
import com.web.sgucharitywebsite.entity.Transaction;
import com.web.sgucharitywebsite.service.TransactionService;

@Controller
@RequestMapping("/admin")
public class AdminTransactionController {
  private TransactionService transactionService;

  @Autowired
  public AdminTransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping({ "", "/transaction", "/transaction/index" })
  public String index(Model model) {
    List<Transaction> transactions = transactionService.findByOrderByVnpPayDateDesc();
    model.addAttribute("transactions", transactions);
    return "admin/transaction/index";
  }


}
