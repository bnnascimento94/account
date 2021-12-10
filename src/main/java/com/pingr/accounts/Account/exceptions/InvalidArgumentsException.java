package com.pingr.accounts.Account.exceptions;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException(String details) {
        super("Parâmetros inválidos: " + details);
    }
}
