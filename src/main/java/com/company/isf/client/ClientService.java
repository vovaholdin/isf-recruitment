package com.company.isf.client;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public  void save(Client client) {
        clientRepository.save(client);
    }


    public Optional<Client> findById(Long aLong) {
        return clientRepository.findById(aLong);
    }


    public List<Client> findAll() {
        return clientRepository.findAll();
    }


    public void delete(Client entity) {
        clientRepository.delete(entity);
    }
}
