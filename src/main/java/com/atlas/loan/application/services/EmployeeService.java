package com.atlas.loan.application.services;

import com.atlas.loan.application.controllers.dto.EmployeeRegisterRequest;
import com.atlas.loan.application.controllers.dto.LoginRequest;
import com.atlas.loan.application.persistance.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Integer doLogin(LoginRequest request);

    String addEmployee(EmployeeRegisterRequest request);

    Employee updateEmployee(Employee employee);

    List<Employee> getAllEmployees();
   Employee getEmployeeById(int employeeId);
}
