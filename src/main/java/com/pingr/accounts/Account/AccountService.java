package com.pingr.accounts.Account;

import com.pingr.accounts.Account.exceptions.InvalidAccountCreationException;
import com.pingr.accounts.Account.exceptions.InvalidArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {


    private final AccountProducerService accountProducerService;

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountProducerService accountProducerService) {
        this.accountRepository = accountRepository;
        this.accountProducerService = accountProducerService;
    }

    public Account createAccount(Account account) {
        if (account == null) throw new InvalidAccountCreationException("conta não pode ser nula");

        try {
            Account newAccount = this.accountRepository.save(account);
            accountProducerService.sendCreatedAccount(newAccount);
            return newAccount;

        } catch (Exception e) {
            throw new InvalidAccountCreationException("conta inválida para criação");
        }
    }

    public Account updateAccount(Account account) {

        Optional<Account> accountCreated = accountRepository.findById(account.getId());

        if(accountCreated.isPresent()){

            try {
                Account newAccount = this.accountRepository.save(account);
                accountProducerService.sendUpdatedAccount(newAccount);
                return newAccount;

            } catch (Exception e) {
                throw new InvalidAccountCreationException("conta inválida para criação");
            }

        }else{
            throw new InvalidAccountCreationException("conta não pode ser nula");
        }




    }


    public boolean deleteAccount(Long id) {

        Optional<Account> accountCreated = accountRepository.findById(id);

        if(accountCreated.isPresent()){

            try {
               accountRepository.delete(accountCreated.get());
               accountProducerService.sendDeletedAccount(accountCreated.get());
                return true;

            } catch (Exception e) {
                throw new InvalidAccountCreationException("conta inválida para criação");
            }

        }else{
            throw new InvalidAccountCreationException("conta não pode ser nula");
        }




    }


    public List<AccountIdAndUsername> searchByUsernameAlike(String usernameAlike) {
        if (usernameAlike.length() == 0) throw new InvalidArgumentsException("termo de busca vazio");

        return this.accountRepository.searchByUsernameAlike(usernameAlike);
    }






}
