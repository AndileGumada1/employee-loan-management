package com.atlas.loan.application.persistance;

import com.atlas.loan.application.persistance.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("select t from Transaction t inner join Loan l on l.loanId=t.loan.loanId where l.employee.id=?1")
    List<Transaction> findTransactionsByEmployeeId(int employeeId);

}
