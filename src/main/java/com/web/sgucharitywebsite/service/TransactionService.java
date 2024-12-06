package com.web.sgucharitywebsite.service;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.entity.Project;
import com.web.sgucharitywebsite.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.query.Param;

public interface TransactionService {
  List<Transaction> findByOrderByVnpPayDateDesc();

  void createTransaction(Transaction transaction);

  List<Transaction> findAllTransaction();

  Double findTotalDonationAmount();

  List<Object[]> findDailyDonationAmountBetweenDates(@Param("startDate") LocalDateTime startDate,
      @Param("endDate") LocalDateTime endDate);

  List<Transaction> findTranSactionByEmail(String email);
}
