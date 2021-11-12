package com.pingr.accounts.Account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{userName}")
    public Account getAccount(@PathVariable("userName") String userName){
        return accountService.getAccount(userName);
    }

    @GetMapping
    public List<AccountIdAndUsername> searchByUsernameAlike(@RequestParam("usernameAlike") String usernameAlike){
        return accountService.searchUsernameAlike(usernameAlike);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.create(account);
    }

    @PutMapping
    public Account updateAccount(@RequestBody Account account){
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/{accountID}")
    public void deleteAccount(@PathVariable("accountID") Long id){
        accountService.delete(id);
    }




}
