package com.pingr.accounts.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account create(Account account){
        return repository.save(account);
    }

    public Account getAccount(String username){
        return repository.findAccountByUsernameOrByEmail(username).orElseThrow(() ->
                new AccountNotFoundException("No records found with this username")
        );
    }

    public void delete(Long id){
        Account account = repository.findById(id).orElseThrow(()->
                new AccountNotFoundException("No record found with this ID"));

        repository.delete(account);
    }


}
