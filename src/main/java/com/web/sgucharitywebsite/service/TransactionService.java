package com.web.sgucharitywebsite.service;

import com.web.sgucharitywebsite.dto.CategoryDto;
import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.entity.Project;
import com.web.sgucharitywebsite.entity.Transaction;

import java.util.List;

public interface TransactionService {

  void createTransaction(Transaction transaction);

  List<Transaction> findAllTransaction();
}
