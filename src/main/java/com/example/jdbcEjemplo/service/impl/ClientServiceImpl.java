package com.example.jdbcEjemplo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jdbcEjemplo.dto.ClientDTO;
import com.example.jdbcEjemplo.model.Client;
import com.example.jdbcEjemplo.repository.ClientRepository;
import com.example.jdbcEjemplo.service.ClientService;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;


	@Override
	public List<Client> getClients() {
		return clientRepository.getClients();
	}

	@Override
	public Client createClient(ClientDTO clientDto) {
		Client client = new Client(clientDto);
		return clientRepository.createClient(client);
	}

	@Override
	public Client getClientById(String id) {
		return clientRepository.getClientById(id);
	}


}
