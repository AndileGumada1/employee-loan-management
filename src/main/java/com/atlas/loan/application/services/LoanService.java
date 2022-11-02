package com.atlas.loan.application.services;

import com.atlas.loan.application.persistance.entity.Loan;

import java.util.List;

public interface LoanService {
    Loan applyLoan(Loan loan);
    public List<Loan> getLoansByEmployeeId(int empId);
    void foreCloseLoan(int loanId);
}
