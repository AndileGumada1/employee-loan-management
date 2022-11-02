package com.atlas.loan.application.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
public class LoanRequest {
     @NotBlank(message = "Amount should not be blank.")
     private String department;
     @NotBlank(message = "Amount should not be blank.")
     private Double loanAmount;
     @NotBlank(message = "Amount should not be blank.")
     private String managerName;
     @NotBlank(message = "Amount should not be blank.")
     private String reason;
}
