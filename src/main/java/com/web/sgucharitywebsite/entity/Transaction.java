package com.web.sgucharitywebsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vnp_OrderInfo")
    private String vnpOrderInfo; // Nội dung giao dịch của vnpay
    @Column(name = "vnp_PayDate")
    private LocalDateTime vnpPayDate; // Thời gian thanh toán
    @Column(name = "vnp_OrderStatus")
    private String vnpOrderStatus; // Trạng thái giao dịch
    @Column(name = "vnp_TransactionNo")
    private String vnpTransactionNo; // Mã giao dịch của VNPay
    @Column(name = "vnp_Amount")
    private Double vnpAmount;
    @ManyToOne
    @JoinColumn(name="project_id", nullable = false)
    private Project project;
}
