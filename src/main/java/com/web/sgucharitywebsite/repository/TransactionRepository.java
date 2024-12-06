package com.web.sgucharitywebsite.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.sgucharitywebsite.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByOrderByVnpPayDateDesc();

    @Query("SELECT SUM(t.vnpAmount) FROM Transaction t")
    Double findTotalDonationAmount();

    @Query("SELECT FUNCTION('DATE', t.vnpPayDate), SUM(t.vnpAmount) " +
            "FROM Transaction t " +
            "WHERE t.vnpPayDate BETWEEN :startDate AND :endDate " +
            "GROUP BY FUNCTION('DATE', t.vnpPayDate)")
    List<Object[]> findDailyDonationAmountBetweenDates(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    List<Transaction> findTranSactionByEmail(String email);
}
