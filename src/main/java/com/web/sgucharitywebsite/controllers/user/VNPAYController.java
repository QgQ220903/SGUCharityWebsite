package com.web.sgucharitywebsite.controllers.user;

import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.entity.Project;
import com.web.sgucharitywebsite.entity.Transaction;
import com.web.sgucharitywebsite.service.AppUserService;
import com.web.sgucharitywebsite.service.ProjectService;
import com.web.sgucharitywebsite.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import com.web.sgucharitywebsite.config.VNPAYService;

import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;

@Controller
// VNPAYController
public class VNPAYController {

    private VNPAYService vnPayService;
    private TransactionService transactionService;
    private ProjectService projectService;
    private int projectId;
    private String projectName;

    @Autowired
    public VNPAYController(TransactionService transactionService, ProjectService projectService, VNPAYService vnPayService) {
        this.transactionService = transactionService;
        this.projectService = projectService;
        this.vnPayService = vnPayService;
    }

    @PostMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
            @RequestParam("orderInfo") String orderInfo, @RequestParam("id") int projectId,
            @RequestParam("name") String projectName,
            HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        System.out.println(projectId);
        System.out.println(projectName);
        this.projectId = projectId;
        this.projectName = projectName;
        return "redirect:" + vnpayUrl;
    }

    @GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        System.out.println("Mã giao dịch: " + request.getParameter("vnp_TransactionNo"));
        System.out.println("Ngày giao dịch: " + request.getParameter("vnp_PayDate"));
        System.out.println("Số tiền giao dịch: " + request.getParameter("vnp_Amount"));
        System.out.println("Nội dung giao dịch: " + request.getParameter("vnp_OrderInfo"));
        System.out.println("Dự án giao dịch: " + this.projectId);
        System.out.println("Dự án giao dịch: " + this.projectName);

        Transaction transaction = new Transaction();

        transaction.setVnpAmount(request.getParameter("vnp_Amount"));
        transaction.setVnpOrderInfo(request.getParameter("vnp_OrderInfo"));
        transaction.setVnpOrderStatus(request.getParameter("paymentStatus"));
        transaction.setVnpPayDate(request.getParameter("vnp_PayDate"));
        transaction.setVnpTransactionNo(request.getParameter("vnp_TransactionNo"));

        Project project = projectService.findProjectByIdEntity(this.projectId);
        transaction.setProject(project);

        transactionService.createTransaction(transaction);

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }
}
