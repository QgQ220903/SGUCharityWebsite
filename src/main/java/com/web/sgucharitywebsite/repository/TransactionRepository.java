package com.web.sgucharitywebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.sgucharitywebsite.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
