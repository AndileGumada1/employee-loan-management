package com.atlas.loan.application.controllers;

import com.atlas.loan.application.controllers.dto.EmployeeRegisterRequest;
import com.atlas.loan.application.controllers.dto.LoginRequest;
import com.atlas.loan.application.persistance.entity.Employee;
import com.atlas.loan.application.services.implementation.EmployeeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@AllArgsConstructor
@Slf4j
@CrossOrigin("https://employee-loan-ui.web.app/")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;
    private final ObjectMapper objectMapper;

    /**
     * @param request
     * @return
     * @throws JsonProcessingException
     */
    @ApiOperation( value = "Register Employee Endpoint")
    @PostMapping
    public ResponseEntity<String> registerEmployee(@RequestBody @Valid EmployeeRegisterRequest request) throws JsonProcessingException {
        log.info("Registering a new employee {} :",objectMapper.writeValueAsString(request));
        String token = employeeService.addEmployee(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

//    /**
//     * @param token
//     * @return
//     */
//    @GetMapping("confirm/{token}")
//    public ResponseEntity<String> verifyAccount(@RequestParam String token){
//       String message = employeeService.verifyAccount(token);
//        return new ResponseEntity<>(message,HttpStatus.OK);
//    }

    /**
     * @param employee
     * @return
     * @throws JsonProcessingException
     */
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) throws JsonProcessingException {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        log.info("Update the employee record",objectMapper.writeValueAsString(employee));
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    /**
     * @return
     */
    //
    @ApiOperation( value = "Fetching All Employees Endpoint")
    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    /**
     * @param loginRequest
     * @return
     * @throws JsonProcessingException
     */
    @ApiOperation( value = "Login Endpoint")
    @PostMapping("/login")
    public ResponseEntity<Integer> doLogin(@RequestBody LoginRequest loginRequest) throws JsonProcessingException {

        log.info("Login request employee {} :",objectMapper.writeValueAsString(loginRequest));

        return new ResponseEntity<>(employeeService.doLogin(loginRequest), HttpStatus.OK);
    }

    /**
     * @param id
     * @return
     */
    @ApiOperation( value = "Get An Employee By Id")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){

        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
}
