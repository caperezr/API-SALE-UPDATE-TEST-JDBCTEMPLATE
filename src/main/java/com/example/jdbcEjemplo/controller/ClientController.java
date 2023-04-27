package com.example.jdbcEjemplo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jdbcEjemplo.dto.ClientDTO;
import com.example.jdbcEjemplo.model.Client;
import com.example.jdbcEjemplo.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/clients")
@Slf4j
public class ClientController {
	@Autowired
	private ClientService clientService;
	
	@GetMapping("")
	public ResponseEntity<List<Client>> getUsers(){
		try {
			List<Client> clients = clientService.getClients();
			return ResponseEntity.status(HttpStatus.OK).body(clients);
		}
		catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping("")
	@Transactional
	public ResponseEntity<Client> createUser(@RequestBody ClientDTO clientDto){
		try {
			Client client = clientService.createClient(clientDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(client);
		}
		catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getClientById(@PathVariable String id){
		try {
			Client client = clientService.getClientById(id);
			return ResponseEntity.status(HttpStatus.OK).body(client);
		}
		catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
}
