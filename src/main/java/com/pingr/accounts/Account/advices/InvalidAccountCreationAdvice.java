package com.pingr.accounts.Account.advices;

import com.pingr.accounts.Account.exceptions.InvalidAccountCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidAccountCreationAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidAccountCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String accountCreationExceptionHandler(InvalidAccountCreationException ex) {
        return ex.getMessage();
    }
}
