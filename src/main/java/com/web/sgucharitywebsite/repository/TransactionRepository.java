package com.web.sgucharitywebsite.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.sgucharitywebsite.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  @Query("SELECT SUM(t.vnpAmount) FROM Transaction t")
  Double findTotalDonationAmount();

  @Query("SELECT SUM(t.vnpAmount) FROM Transaction t WHERE t.vnpPayDate BETWEEN :startDate AND :endDate")
  Double findTotalDonationAmountBetweenDates(@Param("startDate") LocalDateTime startDate,
      @Param("endDate") LocalDateTime endDate);
}
