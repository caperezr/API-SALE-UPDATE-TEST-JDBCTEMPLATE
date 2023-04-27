package com.example.jdbcEjemplo.repository;

import com.example.jdbcEjemplo.model.Client;
import com.example.jdbcEjemplo.repository.impl.ClientRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientRepositoryTest {
	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private SimpleJdbcInsert simpleJdbcInsert;

	@InjectMocks
	private ClientRepositoryImpl clientRepositoryImpl;

	@Test
	void getClientsTest() {
		Client client1 = new Client(1, "Cristhian", "999999999");
		Client client2 = new Client(2, "Javier", "999999999");
		List<Client> expectedClients = new ArrayList<>();
		expectedClients.add(client1);
		expectedClients.add(client2);
		when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedClients);
		List<Client> actualClients = clientRepositoryImpl.getClients();
		assertEquals(expectedClients.size(), actualClients.size());
		assertEquals(expectedClients, actualClients);
	}

	@Test
	void getClientByIdTest() {
		String id = "1";
		Client expectedClient = new Client(1, "Cristhian", "999999999");
		when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn(expectedClient);
		Client actualClient = clientRepositoryImpl.getClientById(id);
		assertNotNull(actualClient);
		assertEquals(actualClient.getName(), expectedClient.getName());
		assertEquals(actualClient.getPhone(), expectedClient.getPhone());
	}

	@Test
	void mapToClientTest() throws SQLException {
		ResultSet resultSet = mock(ResultSet.class);
		Client expectedClient = new Client(1, "Cristhian", "999999999");
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getString("name")).thenReturn("Cristhian");
		when(resultSet.getString("phone")).thenReturn("999999999");
		Client actualClient = clientRepositoryImpl.mapToClient(resultSet);
		assertNotNull(actualClient);
		assertEquals(actualClient.getId(), expectedClient.getId());
		assertEquals(actualClient.getName(), expectedClient.getName());
		assertEquals(actualClient.getPhone(), expectedClient.getPhone());

	}




}
