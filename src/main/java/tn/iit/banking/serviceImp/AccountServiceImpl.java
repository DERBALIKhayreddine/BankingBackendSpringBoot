package tn.iit.banking.serviceImp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.banking.entities.Account;
import tn.iit.banking.entities.AccountResponse;
import tn.iit.banking.entities.Client;
import tn.iit.banking.exceptions.ResourceNotFoundException;
import tn.iit.banking.repositories.AccountRepository;
import tn.iit.banking.repositories.ClientRepository;
import tn.iit.banking.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public Account createAccount(Long clientId, Account account) {
        // Validate client existence
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + clientId));

        // Associate the client with the account
        account.setClient(client);

        // Save the account
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        // Fetch account by ID or throw exception if not found
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
    }

    @Override
    public List<Account> getAllAccounts() {
        // Fetch all accounts from the repository
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public Account updateAccount(Long id, Account account) {
        // Fetch the existing account
        Account existingAccount = getAccountById(id);

        // Update the account details
        existingAccount.setAccountBalance(account.getAccountBalance());
        existingAccount.setRib(account.getRib()); // Update the RIB if provided

        // Save and return the updated account
        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        // Check if account exists before deleting
        if (!accountRepository.existsById(id)) {
            throw new ResourceNotFoundException("Account not found with id " + id);
        }

        // Delete the account
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountResponse> getAllAccountResponses() {
        // Fetch all accounts and map them to AccountResponse DTOs
        return accountRepository.findAll().stream()
                .map(account -> new AccountResponse(
                        account.getId(),
                        account.getRib(),
                        account.getAccountBalance(),
                        account.getClient().getNom() + " " + account.getClient().getPrenom()
                ))
                .collect(Collectors.toList());
    }
}