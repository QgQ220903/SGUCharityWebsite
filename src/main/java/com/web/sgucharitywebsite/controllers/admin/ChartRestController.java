package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.repository.ProjectRepository;
import com.web.sgucharitywebsite.repository.helper.CurrencyFormatter;
import com.web.sgucharitywebsite.service.AppUserService;
import com.web.sgucharitywebsite.service.ProjectService;
import com.web.sgucharitywebsite.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ChartRestController {
    private ProjectService projectService;
    private AppUserService appUserService;
    private TransactionService transactionService;

    @Autowired
    public ChartRestController(ProjectService projectService,
            AppUserService appUserService,
            TransactionService transactionService) {
        this.projectService = projectService;
        this.appUserService = appUserService;
        this.transactionService = transactionService;
    }

    @GetMapping("/api/countAllProjects")
    public int countAllProjects() {
        return projectService.countAllProjects();
    }

    @GetMapping("/api/countAllUsers")
    public int countAllUser() {
        return appUserService.countAllUsers();
    }

    // API trả về số lượng dự án theo thể loại
    @GetMapping("/api/projects-by-category")
    public List<Object[]> getProjectsByCategory() {
        return projectService.countProjectsByCategory(); // Trả về danh sách số lượng dự án theo thể loại
    }

    @GetMapping("/api/projects-amount")
    public List<Object[]> getProjectsAndAmount() {
        return projectService.getProjectsAndAmount();
    }

    @GetMapping("/api/total-donation")
    public String findTotalDonationAmount() {
        return CurrencyFormatter.formatToVND(transactionService.findTotalDonationAmount());
    }

    @GetMapping("/api/donations/total")
    public ResponseEntity<Double> getTotalDonationAmount(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        Double totalAmount = transactionService.findTotalDonationAmountBetweenDates(startDate, endDate);
        return ResponseEntity.ok(totalAmount);
    }

}
