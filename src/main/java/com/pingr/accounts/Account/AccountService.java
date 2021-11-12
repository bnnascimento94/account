package com.pingr.accounts.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.repository = accountRepository;
    }

    public Account create(Account account){

        if(account == null) throw new IllegalStateException("This Account cannot be null");

        if (repository.findAccountByUsername(account.getUsername()).isPresent()
        || repository.findAccountByEmail(account.getEmail()).isPresent()){

            throw new IllegalStateException("This Account already exists");

        }

        return repository.save(account);
    }

    public Account updateAccount(Account account){

        if(account == null) throw new IllegalStateException("This Account cannot be null");

        if (repository.findAccountByUsername(account.getUsername()).isPresent()
                || repository.findAccountByEmail(account.getEmail()).isPresent()){

            return repository.save(account);

        }
        throw new IllegalStateException("This Account doesn't exist");

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

    public List<AccountIdAndUsername> searchUsernameAlike(String usernameAlike){
        if(usernameAlike.length() ==0) throw  new IllegalStateException("O termo de busca não pode ser vazio");

        return repository.searchByUserNameAlike(usernameAlike);
    }

}
