package com.example.jdbcEjemplo.model;

import com.example.jdbcEjemplo.dto.ClientDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	private int id;
	private String name;
	private String phone;
	
	public Client(ClientDTO clientDTO) {
		this.name = clientDTO.getName();
		this.phone =clientDTO.getPhone();
	}
}
