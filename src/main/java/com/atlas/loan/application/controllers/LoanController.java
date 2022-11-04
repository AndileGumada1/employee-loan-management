package com.atlas.loan.application.controllers;

import com.atlas.loan.application.controllers.dto.TransactionResponse;
import com.atlas.loan.application.persistance.entity.Loan;
import com.atlas.loan.application.services.implementation.LoanServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import static java.util.Map.of;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin("https://employee-loan-ui.web.app/")
@RestController
@RequestMapping("api/v1/loans")
@AllArgsConstructor
@Slf4j
public class LoanController {
        private final LoanServiceImpl loanService;
        private final ObjectMapper objectMapper;
        @PostMapping
        public ResponseEntity<Loan> applyLoan(@RequestBody Loan loan) throws JsonProcessingException {
                log.info("Request for loan apply{} :",objectMapper.writeValueAsString(loan));
                Loan response = loanService.applyLoan(loan);
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

//        @PostMapping
//        public ResponseEntity<TransactionResponse> applicationForLoan(@RequestBody Loan loan) {
//                return ResponseEntity.ok(
//                        TransactionResponse.builder()
//                                .transactionTime(now())
//                                .data(of("loan", loanService.applyLoan(loan)))
//                                .message("Loan Application Submitted")
//                                .status(CREATED)
//                                .statusCode(OK.value())
//                                .build()
//                );
//        }

        @GetMapping("/employee/{id}")
        public ResponseEntity<List<Loan>> getLoansByEmployeeId(@PathVariable int id) {
                return new ResponseEntity<>(loanService.getLoansByEmployeeId(id), HttpStatus.OK);
        }

        @DeleteMapping("/foreclose/{loanId}")
        public ResponseEntity<String> forecloseLoan(@PathVariable int loanId) {
                loanService.foreCloseLoan(loanId);
                return new ResponseEntity<>("Loan Foreclosed with Loan Id: " + loanId, HttpStatus.OK);
        }

}
