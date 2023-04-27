package com.example.jdbcEjemplo.service;

import java.util.List;

import com.example.jdbcEjemplo.dto.ClientDTO;
import com.example.jdbcEjemplo.model.Client;

public interface ClientService {
	public List<Client> getClients();
	public Client createClient(ClientDTO client);
	public Client getClientById(String id);
}
