package com.pingr.accounts.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    @Query("SELECT acc FROM Account acc WHERE acc.username = ?1 OR acc.email = ?1")
    Optional<Account> findAccountByUsernameOrByEmail(String usernameOrEmail);

    Optional<Account> findAccountByUsername(String username);

    Optional<Account> findAccountByEmail(String email);

    @Query(value = "SELECT a.id, a.username from Account a where a.username like %?1% ", nativeQuery = true)
    List<AccountIdAndUsername> searchByUserNameAlike(String username);


}
