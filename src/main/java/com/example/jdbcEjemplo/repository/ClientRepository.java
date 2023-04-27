package com.example.jdbcEjemplo.repository;

import java.util.List;

import com.example.jdbcEjemplo.model.Client;

public interface ClientRepository {
	public List<Client> getClients();
	public Client createClient(Client client);
	public Client getClientById(String id);
}
