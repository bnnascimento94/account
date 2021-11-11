package com.pingr.accounts.Account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/{userName}")
    public Account getAccount(@PathVariable("userName") String userName){
        return accountService.getAccount(userName);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.create(account);
    }

    @DeleteMapping("/{accountID}")
    public void deleteAccount(@PathVariable("accountID") Long id){
        accountService.delete(id);
    }




}
