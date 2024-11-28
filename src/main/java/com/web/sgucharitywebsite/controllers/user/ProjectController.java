package com.web.sgucharitywebsite.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.sgucharitywebsite.config.VNPAYService;
import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.entity.Project;
import com.web.sgucharitywebsite.repository.AppUserRepository;
import com.web.sgucharitywebsite.service.CategoryService;
import com.web.sgucharitywebsite.service.ProjectService;

import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.List;

@Controller
public class ProjectController {
    private AppUserRepository appUserRepository;
    private ProjectService projectService;
    private VNPAYService vnpayService;

    @Autowired
    public ProjectController(AppUserRepository appUserRepository, VNPAYService vnpayService,
            ProjectService projectService) {
        this.projectService = projectService;
        this.appUserRepository = appUserRepository;
        this.vnpayService = vnpayService;
    }

    @GetMapping("/project")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }
        List<ProjectDto> projectDtoList = projectService.findAllProjects();
        model.addAttribute("projects", projectDtoList);
        return "project-list";
    }

    @GetMapping("/project/{id}")
    public String detail(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }
        return "project-detail";
    }

    @GetMapping("/project/detail/{id}")
    public String getMethodName(@PathVariable("id") long projectId, Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }
        ProjectDto projectDto = projectService.findProjectById(projectId);
        model.addAttribute("project", projectDto);
        return "project-detail";
    }

    // Chuyển hướng người dùng đến cổng thanh toán VNPAY
    // @PostMapping("/submitOrder")
    // public String submidOrder(@RequestParam("amount") int orderTotal,
    // @RequestParam("orderInfo") String orderInfo,
    // HttpServletRequest request) {
    // String baseUrl = request.getScheme() + "://" + request.getServerName() + ":"
    // + request.getServerPort();
    // String vnpayUrl = vnPayService.createOrder(request, orderTotal, orderInfo,
    // baseUrl);
    // return "redirect:" + vnpayUrl;
    // }

    // // Sau khi hoàn tất thanh toán, VNPAY sẽ chuyển hướng trình duyệt về URL này
    // @GetMapping("/vnpay-payment-return")
    // public String paymentCompleted(HttpServletRequest request, Model model) {
    // int paymentStatus = vnPayService.orderReturn(request);

    // String orderInfo = request.getParameter("vnp_OrderInfo");
    // String paymentTime = request.getParameter("vnp_PayDate");
    // String transactionId = request.getParameter("vnp_TransactionNo");
    // String totalPrice = request.getParameter("vnp_Amount");

    // model.addAttribute("orderId", orderInfo);
    // model.addAttribute("totalPrice", totalPrice);
    // model.addAttribute("paymentTime", paymentTime);
    // model.addAttribute("transactionId", transactionId);

    // return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    // }

}
