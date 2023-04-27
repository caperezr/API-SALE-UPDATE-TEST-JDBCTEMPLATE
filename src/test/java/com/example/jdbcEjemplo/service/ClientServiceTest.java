package com.example.jdbcEjemplo.service;

import com.example.jdbcEjemplo.dto.ClientDTO;
import com.example.jdbcEjemplo.model.Client;
import com.example.jdbcEjemplo.repository.ClientRepository;
import com.example.jdbcEjemplo.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

	@Mock
	private ClientRepository clientRepository;

	@InjectMocks
	private ClientServiceImpl clientServiceImpl;

	@Test
	void getClientsTest() {
		Client client1 = new Client(1, "Cristhian", "999999999");
		Client client2 = new Client(2, "Javier", "999999999");
		List<Client> expectedClients = new ArrayList<>();
		expectedClients.add(client1);
		expectedClients.add(client2);

		when(clientRepository.getClients()).thenReturn(expectedClients);

		List<Client> actualClients = clientServiceImpl.getClients();
		assertEquals(expectedClients.size(), actualClients.size());
		assertEquals(expectedClients, actualClients);
	}

	@Test
	void getClientByIdTest() {
		String id = "1";
		Client expectedClient = new Client(1, "percy", "956123982");

		when(clientRepository.getClientById(id)).thenReturn(expectedClient);

		Client actualClient = clientServiceImpl.getClientById(id);

		assertNotNull(actualClient);
		assertEquals(actualClient.getName(), expectedClient.getName());
		assertEquals(actualClient.getPhone(), expectedClient.getPhone());
	}

	@Test
	void createClientTest() {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setName("percy");
		clientDTO.setPhone("956123982");

		Client expectedClient = new Client();
		expectedClient.setName("percy");
		expectedClient.setPhone("956123982");

		when(clientRepository.createClient(any(Client.class))).thenReturn(expectedClient);

		Client actualClient = clientServiceImpl.createClient(clientDTO);

		assertNotNull(actualClient);
		assertEquals(actualClient.getName(), expectedClient.getName());
		assertEquals(actualClient.getPhone(), expectedClient.getPhone());
	}

	@Test
	void getClientsTest1() {
		ClientService clientService = mock(ClientService.class);
		when(clientService.getClients())
				.thenReturn(Arrays.asList(new Client(1, "Cristhian", "999999999"), new Client(2, "Juan", "090989098")));
		List<Client> resultado = clientService.getClients();
		assertEquals(2, resultado.size());
		assertEquals("Cristhian", resultado.get(0).getName());
		assertEquals("999999999", resultado.get(0).getPhone());
		assertEquals("Juan", resultado.get(1).getName());
		assertEquals("090989098", resultado.get(1).getPhone());
	}

	@Test
	void createClientTest1() {
		ClientDTO clientDTO = new ClientDTO();
		ClientService clientService = mock(ClientService.class);
		clientDTO.setName("Cristhian");
		clientDTO.setPhone("909909909");
		Client expectedClient = new Client(clientDTO);
		expectedClient.setId(1);
		when(clientRepository.createClient(any(Client.class))).thenReturn(expectedClient);
		Client actualClient = clientRepository.createClient(expectedClient);
		assertEquals(expectedClient, actualClient);
	}

	@Test
	void getClientByIdTets1() {
		Client client = new Client(1, "Cristhian", "999999999");
		// Simulamos el comportamiento del repositorio y le indicamos que cuando sea 1
		// me retorne el client
		when(clientRepository.getClientById(eq("1"))).thenReturn(client);
		Client actual = clientRepository.getClientById("1");
		assertEquals(client, actual);
	}

}
