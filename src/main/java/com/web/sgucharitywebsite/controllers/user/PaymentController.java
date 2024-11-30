package com.web.sgucharitywebsite.controllers.user;

import com.web.sgucharitywebsite.dto.PaymentResponse;
import com.web.sgucharitywebsite.service.Impl.MomoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @Autowired
    private MomoService momoService;

    @GetMapping("/payment")
    public String payment(@RequestParam(value = "amount", required = false) String amount, Model model) {
        if (amount == null || amount.isEmpty()) {
            // Nếu không có amount, hiển thị form nhập số tiền
            return "payment";
        }

        String orderId = "order_" + System.currentTimeMillis();
        String orderInfo = "Thanh toán hóa đơn #" + orderId;

        PaymentResponse response = momoService.createPayment(amount, orderId, orderInfo);
        if (response != null && response.getResultCode() == 0) {
            model.addAttribute("payUrl", response.getPayUrl());
            return "payment-qr";
        } else {
            model.addAttribute("error", "Không thể thực hiện thanh toán.");
            return "error";
        }
    }

    @GetMapping("/payment/return")
    public String paymentReturn(@RequestParam String resultCode, Model model) {
        if ("0".equals(resultCode)) {
            model.addAttribute("message", "Thanh toán thành công!");
        } else {
            model.addAttribute("message", "Thanh toán thất bại.");
        }
        return "payment-result";
    }
}

