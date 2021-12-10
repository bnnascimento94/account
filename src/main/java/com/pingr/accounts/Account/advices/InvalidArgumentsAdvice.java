package com.pingr.accounts.Account.advices;

import com.pingr.accounts.Account.exceptions.InvalidArgumentsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidArgumentsAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidArgumentsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidArgumentsExceptionHandler(InvalidArgumentsException ex) {
        return ex.getMessage();
    }
}
