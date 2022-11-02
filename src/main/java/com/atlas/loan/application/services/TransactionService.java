package com.atlas.loan.application.services;

import com.atlas.loan.application.persistance.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    List<Transaction> getTransactionsByEmployeeId(int empId);
}
