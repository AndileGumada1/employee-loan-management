package com.atlas.loan.application.controllers.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private LocalDateTime transactionTime;
    private String message;//
    private HttpStatus status;
    private Map<?,?> data;
}
