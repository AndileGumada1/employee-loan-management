package com.atlas.loan.application.services.implementation;

import com.atlas.loan.application.exceptions.EmployeeNotFoundException;
import com.atlas.loan.application.exceptions.LoanNotFoundException;
import com.atlas.loan.application.exceptions.TransactionFailedException;
import com.atlas.loan.application.exceptions.TransactionsNotFoundException;
import com.atlas.loan.application.persistance.EmployeeRepository;
import com.atlas.loan.application.persistance.LoanRepository;
import com.atlas.loan.application.persistance.TransactionRepository;
import com.atlas.loan.application.persistance.entity.Employee;
import com.atlas.loan.application.persistance.entity.Loan;
import com.atlas.loan.application.persistance.entity.Transaction;
import com.atlas.loan.application.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final EmployeeRepository employeeRepository;
    private final LoanRepository loanRepository;
    private final TransactionRepository transactionRepository;
    @Override
    public Transaction  addTransaction(Transaction transaction) {
        int loanId = transaction.getLoan().getLoanId();
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow( () -> new LoanNotFoundException("Loan Not Found: " + loanId));
        loan.addTransaction(transaction);
        transaction.setLoan(loan);
        try {
            return transactionRepository.save(transaction);
        }catch (Exception e){
            throw new TransactionFailedException("Transaction Failed for LoanId :" + loanId);
        }
    }

    @Override
    public List<Transaction> getTransactionsByEmployeeId(int empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow( () -> new EmployeeNotFoundException("Employee Not Found:"+empId));
        try {
            List<Transaction> transactions = transactionRepository.findTransactionsByEmployeeId(empId);
            return transactions;
        }catch (Exception e){
            throw new TransactionsNotFoundException("Transactions Not Found for EmployeeId :" + empId);
        }
    }
}
