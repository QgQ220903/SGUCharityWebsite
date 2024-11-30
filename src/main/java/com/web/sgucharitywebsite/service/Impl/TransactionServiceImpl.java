package com.web.sgucharitywebsite.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sgucharitywebsite.entity.Transaction;
import com.web.sgucharitywebsite.repository.TransactionRepository;
import com.web.sgucharitywebsite.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
  private TransactionRepository transactionRepository;

  @Autowired
  public TransactionServiceImpl(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Override
  public void createTransaction(Transaction transaction) {
    transactionRepository.save(transaction);
  }

  @Override
  public List<Transaction> findAllTransaction() {
    List<Transaction> transactions = transactionRepository.findAll();
    return transactions;
  }

}
