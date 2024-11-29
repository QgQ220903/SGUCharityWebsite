package com.web.sgucharitywebsite.service.Impl;

import com.web.sgucharitywebsite.dto.PaymentRequest;
import com.web.sgucharitywebsite.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class MomoService {

    @Value("${momo.partnerCode}")
    private String partnerCode;

    @Value("${momo.accessKey}")
    private String accessKey;

    @Value("${momo.secretKey}")
    private String secretKey;

    @Value("${momo.endpoint}")
    private String endpoint;

    @Value("${momo.returnUrl}")
    private String returnUrl;

    @Value("${momo.notifyUrl}")
    private String notifyUrl;

    public PaymentResponse createPayment(String amount, String orderId, String orderInfo) {
        try {
            String requestId = String.valueOf(System.currentTimeMillis());
            String extraData = ""; // Nếu cần thêm dữ liệu

            // Tạo signature
            String rawSignature = String.format(
                    "accessKey=%s&amount=%s&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=captureWallet",
                    accessKey, amount, extraData, notifyUrl, orderId, orderInfo, partnerCode, returnUrl, requestId
            );

            String signature = hmacSHA256(rawSignature, secretKey);

            // Tạo request
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setPartnerCode(partnerCode);
            paymentRequest.setRequestId(requestId);
            paymentRequest.setAmount(amount);
            paymentRequest.setOrderId(orderId);
            paymentRequest.setOrderInfo(orderInfo);
            paymentRequest.setRedirectUrl(returnUrl);
            paymentRequest.setIpnUrl(notifyUrl);
            paymentRequest.setExtraData(extraData);
            paymentRequest.setSignature(signature);

            // Gửi request đến MoMo
            RestTemplate restTemplate = new RestTemplate();
            PaymentResponse response = restTemplate.postForObject(endpoint, paymentRequest, PaymentResponse.class);

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String hmacSHA256(String data, String secret) throws Exception {
        Mac hasher = Mac.getInstance("HmacSHA256");
        hasher.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
        byte[] hash = hasher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }
}

