package tn.iit.banking.serviceImp;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.banking.entities.Account;
import tn.iit.banking.repositories.AccountRepository;
import tn.iit.banking.service.AccountService;

import java.util.List;
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found with id " + id));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Account existingAccount = getAccountById(id);
        existingAccount.setAccountBalance(account.getAccountBalance());
        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}