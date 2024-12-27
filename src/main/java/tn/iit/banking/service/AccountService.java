package tn.iit.banking.service;

import tn.iit.banking.entities.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account getAccountById(Long id);
    List<Account> getAllAccounts();
    Account updateAccount(Long id, Account account);
    void deleteAccount(Long id);
}
