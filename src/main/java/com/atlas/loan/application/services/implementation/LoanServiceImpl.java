package com.atlas.loan.application.services.implementation;

import com.atlas.loan.application.exceptions.EmployeeNotFoundException;
import com.atlas.loan.application.exceptions.LoanNotFoundException;
import com.atlas.loan.application.persistance.EmployeeRepository;
import com.atlas.loan.application.persistance.LoanRepository;
import com.atlas.loan.application.persistance.TransactionRepository;
import com.atlas.loan.application.persistance.entity.Employee;
import com.atlas.loan.application.persistance.entity.Loan;
import com.atlas.loan.application.persistance.entity.Transaction;
import com.atlas.loan.application.services.LoanService;
import com.atlas.loan.application.services.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class LoanServiceImpl implements LoanService {

    private final TransactionServiceImpl transactionService;
    private final LoanRepository loanRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public Loan applyLoan(Loan loan) {
        int employeeId = loan.getEmployee().getId();

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found: " + employeeId));
        employee.addLoan(loan);
        loan.setEmployee(employee);
        Transaction transaction = new Transaction();
        transaction.setStatus("New");
        transaction.setTransactionTime(LocalDateTime.now());
        log.info("Get the employee by ID......",employee);
        transaction.setLoan(loan);
        loanRepository.save(loan);
        transactionService.addTransaction(transaction);
        return loan;
    }

    @Override
    public List<Loan> getLoansByEmployeeId(int empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found: " + empId));
        return employee.getLoans();
    }
    @Override
    public void foreCloseLoan(int loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
        loanRepository.delete(loan);
    }
}
