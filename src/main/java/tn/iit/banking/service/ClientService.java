// Client Service Interface
package tn.iit.banking.service;

import tn.iit.banking.entities.Client;
import java.util.List;

public interface ClientService {
    Client createClient(Client client);
    Client getClientById(Long id);
    List<Client> getAllClients();
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);
}