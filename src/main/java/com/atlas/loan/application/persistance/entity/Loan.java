package com.atlas.loan.application.persistance.entity;

import com.atlas.loan.application.persistance.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanId;
    private double loanAmount;
    private String reason;
    private String managerName;

    @ManyToOne
    @JoinColumn(name = "id", updatable = false)
    private Employee employee;

    @OneToMany(mappedBy = "loan",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();
    public void addTransaction(Transaction transaction) {
        transaction.setLoan(this);
        this.getTransactions().add(transaction);
    }
}
