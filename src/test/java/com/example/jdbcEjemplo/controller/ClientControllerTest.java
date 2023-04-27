package com.example.jdbcEjemplo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.jdbcEjemplo.dto.ClientDTO;
import com.example.jdbcEjemplo.model.Client;
import com.example.jdbcEjemplo.service.ClientService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClientController.class)
class ClientControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClientService clientService;
	
	@Test
	void getClientsTest() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Client client = new Client(1, "percy", "956123982");
		Client client2 = new Client(2, "renzo", "956123983");
		List<Client> expectedClients = new ArrayList<>();
		expectedClients.add(client);
		expectedClients.add(client2);
		int expectedStatus = 200;
		
		String uri = "/v1/clients";
		
		when(clientService.getClients()).thenReturn(expectedClients);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int actualStatus = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		List<Client> actualClients = mapper.readValue(content,
									new TypeReference<List<Client>>(){});
		
		assertEquals(expectedStatus, actualStatus);
		assertEquals(expectedClients.size(), actualClients.size());
		assertEquals(expectedClients, actualClients);
	}
	
	@Test
	void getClientsErrorTest() throws Exception{

		int expectedStatus = 500;
		
		String uri = "/v1/clients";
		
		when(clientService.getClients()).thenThrow(new NullPointerException("Error"));
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int actualStatus = mvcResult.getResponse().getStatus();
		
		assertEquals(expectedStatus, actualStatus);
	}
	
	@Test
	void createClientTest() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setName("percy");
		clientDTO.setPhone("956123982");
		
		Client expectedClient = new Client(1, "percy", "956123982");
		int expectedStatus = 201;
		
		String uri = "/v1/clients";
		
		String bodyString = mapper.writeValueAsString(clientDTO);
		
		when(clientService.createClient(any(ClientDTO.class))).thenReturn(expectedClient);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(bodyString)).andReturn();
		int actualStatus = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		Client actualClient = mapper.readValue(content, Client.class);
		
		assertEquals(expectedStatus, actualStatus);
		assertEquals(actualClient.getName(), expectedClient.getName());
		assertEquals(actualClient.getPhone(), expectedClient.getPhone());
	}
	
	@Test
	void createClientErrorTest() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setName("percy");
		clientDTO.setPhone("956123982");
		
		int expectedStatus = 500;
		
		String uri = "/v1/clients";
		
		String bodyString = mapper.writeValueAsString(clientDTO);
		
		when(clientService.createClient(any(ClientDTO.class))).thenThrow(new NullPointerException("Error"));
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(bodyString)).andReturn();
		int actualStatus = mvcResult.getResponse().getStatus();
		
		assertEquals(expectedStatus, actualStatus);
	}
}
