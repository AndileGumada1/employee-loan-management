package com.atlas.loan.application.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionsId;
    private LocalDateTime transactionTime;
    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "loanId")
    private Loan loan;
}
