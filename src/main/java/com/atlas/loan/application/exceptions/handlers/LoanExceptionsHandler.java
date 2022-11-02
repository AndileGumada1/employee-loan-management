package com.atlas.loan.application.exceptions.handlers;

import com.atlas.loan.application.exceptions.EmployeeNotFoundException;
import com.atlas.loan.application.exceptions.LoanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice()
public class LoanExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoanNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleLoanNotFoundExceptions(LoanNotFoundException exception, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message",exception.getMessage());
        body.put("details",request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
