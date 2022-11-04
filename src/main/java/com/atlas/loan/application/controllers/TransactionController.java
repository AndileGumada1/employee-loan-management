package com.atlas.loan.application.controllers;

import com.atlas.loan.application.persistance.entity.Transaction;
import com.atlas.loan.application.services.implementation.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://employee-loan-ui.web.app/")
@RequestMapping("api/v1/transactions")
@AllArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionServiceImpl transactionService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction trans) {
        return new ResponseEntity<>(transactionService.addTransaction(trans), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByEmployeeId(@PathVariable int id) {
        return new ResponseEntity<>(transactionService.getTransactionsByEmployeeId(id), HttpStatus.OK);
    }
}
