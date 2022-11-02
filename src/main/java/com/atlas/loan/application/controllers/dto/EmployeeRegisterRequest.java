package com.atlas.loan.application.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
public class EmployeeRegisterRequest {
    @NotBlank(message = "First Name should not be blank.")
    private String firstName;

    @NotBlank(message = "Last Name should not be blank.")
    private String lastName;

    @NotBlank(message = "email should not be blank.")
    private String email;

    @NotBlank(message = "Password should not be blank.")
    private String password;

    private String confirmPassword;
    @NotBlank(message = "Employee Number should not be blank.")
    private String employeeNumber;

    @NotBlank(message = "Branch Name should not be blank.")
    private String branchName;
    @NotBlank(message = "Department should not be blank.")
    private String department;

}
