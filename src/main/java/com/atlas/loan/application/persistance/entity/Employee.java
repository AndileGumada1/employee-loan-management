package com.atlas.loan.application.persistance.entity;

import com.atlas.loan.application.persistance.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@ToString @EqualsAndHashCode
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Department department;
    @JsonIgnore
    private long phone;
    private String email;
    private String password;
    private String confirmPassword;
    private String employeeNumber;
    private String branchName;
    private double walletAmt;
    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Loan> loans = new ArrayList<>();
    /**
     * the method below will add Loan to LoansList
     *  also serves the purpose to avoid cyclic references.
     */
    public void addLoan(Loan loan) {
        loan.setEmployee(this); // this will avoid nested cascade
        this.getLoans().add(loan);
    }
}
