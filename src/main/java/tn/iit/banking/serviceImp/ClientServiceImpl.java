package tn.iit.banking.serviceImp;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.banking.entities.Client;
import tn.iit.banking.repositories.ClientRepository;
import tn.iit.banking.service.ClientService;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found with id " + id));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client existingClient = getClientById(id);
        existingClient.setNom(client.getNom());
        existingClient.setPrenom(client.getPrenom());
        existingClient.setRib(client.getRib());
        return clientRepository.save(existingClient);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}