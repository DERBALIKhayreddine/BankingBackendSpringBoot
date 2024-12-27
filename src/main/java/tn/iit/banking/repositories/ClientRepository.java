package tn.iit.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.banking.entities.Client;

public interface ClientRepository  extends JpaRepository<Client, Long> {
}
