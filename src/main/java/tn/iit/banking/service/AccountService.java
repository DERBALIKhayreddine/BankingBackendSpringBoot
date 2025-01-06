package tn.iit.banking.service;

import tn.iit.banking.entities.Account;
import tn.iit.banking.entities.AccountResponse;

import java.util.List;

public interface AccountService {

    Account createAccount(Long clientId, Account account);


    Account getAccountById(Long id);

    List<Account> getAllAccounts();


    List<AccountResponse> getAllAccountResponses();

    Account updateAccount(Long id, Account account);


    void deleteAccount(Long id);
}