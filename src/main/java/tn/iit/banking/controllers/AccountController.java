package tn.iit.banking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.banking.entities.Account;
import tn.iit.banking.entities.AccountResponse;
import tn.iit.banking.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountService accountService;

    // Create Account and associate it with a client
    @PostMapping("/{clientId}")
    public ResponseEntity<?> createAccount(@PathVariable Long clientId, @RequestBody Account account) {
        if (clientId == null) {
            return ResponseEntity.badRequest().body("Client ID is required");
        }

        try {
            Account createdAccount = accountService.createAccount(clientId, account);
            AccountResponse response = new AccountResponse(
                    createdAccount.getId(),
                    createdAccount.getRib(),
                    createdAccount.getAccountBalance(),
                    createdAccount.getClient().getNom() + " " + createdAccount.getClient().getPrenom()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating account: " + e.getMessage());
        }
    }

    // Get Account by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        try {
            Account account = accountService.getAccountById(id);
            AccountResponse response = new AccountResponse(
                    account.getId(),
                    account.getRib(),
                    account.getAccountBalance(),
                    account.getClient().getNom() + " " + account.getClient().getPrenom()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> accounts = accountService.getAllAccounts().stream()
                .map(account -> new AccountResponse(
                        account.getId(),
                        account.getRib(),
                        account.getAccountBalance(),
                        account.getClient().getNom() + " " + account.getClient().getPrenom()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(accounts);
    }

    // Update an account
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        try {
            Account updatedAccount = accountService.updateAccount(id, account);
            AccountResponse response = new AccountResponse(
                    updatedAccount.getId(),
                    updatedAccount.getRib(),
                    updatedAccount.getAccountBalance(),
                    updatedAccount.getClient().getNom() + " " + updatedAccount.getClient().getPrenom()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating account: " + e.getMessage());
        }
    }

    // Delete an account
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
