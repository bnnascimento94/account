package com.pingr.accounts.Account.exceptions;

public class InvalidAccountCreationException extends RuntimeException {
    public InvalidAccountCreationException(String details) {
        super("Falha ao criar Conta: " + details);
    }
}
