package tn.iit.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.banking.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}