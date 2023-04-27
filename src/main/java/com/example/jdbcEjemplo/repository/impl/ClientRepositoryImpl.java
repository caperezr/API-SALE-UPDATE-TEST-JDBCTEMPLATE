package com.example.jdbcEjemplo.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.jdbcEjemplo.model.Client;
import com.example.jdbcEjemplo.repository.ClientRepository;

@Repository
public class ClientRepositoryImpl implements ClientRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SimpleJdbcInsert simpleJdbcInsert;


	public ClientRepositoryImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
		this.jdbcTemplate = jdbcTemplate;
		this.simpleJdbcInsert = simpleJdbcInsert;
	}



	@Override
	public List<Client> getClients() {
		String sql = "SELECT * FROM Client";
		List<Client> clients = jdbcTemplate.query(sql, 
				(rs, rowNum) -> mapToClient(rs));
		return clients;
	}
	
	public Client mapToClient(ResultSet rs) throws SQLException {
        return new Client(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("phone"));
	}

	@Override
	public Client createClient(Client client) {
		SimpleJdbcInsert simpleJdbcInsert =
	            new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("Client")
                .usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("name", client.getName());
	    parameters.put("phone", client.getPhone());
	    int id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
	    client.setId(id);
	    return client;
	}




	@Override
	public Client getClientById(String id) {
		String sql = "SELECT * FROM Client WHERE id = ?";
        Client client = jdbcTemplate.queryForObject(sql, 
        		(rs, rowNum) -> mapToClient(rs), id);
        return client;
	}
}
