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
import com.example.jdbcEjemplo.model.Sale;
import com.example.jdbcEjemplo.repository.SaleRepository;

@Repository
public class SaleRepositoryImpl implements SaleRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Sale> getSales() {
		String sql = "SELECT * FROM Sale";
		List<Sale> sales = jdbcTemplate.query(sql, 
				(rs, rowNum) -> mapToSale(rs));
		return sales;
	}
	
	public Sale mapToSale(ResultSet rs) throws SQLException {
        Sale sale = new Sale();
        sale.setId(rs.getInt("id"));
        sale.setTotal(rs.getDouble("total"));
        Client client = new Client();
        client.setId(rs.getInt("idClient"));
        sale.setClient(client);
		return sale;
	}

	@Override
	public Sale createSale(Sale sale) {
		SimpleJdbcInsert simpleJdbcInsert =
	            new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("Sale")
                .usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("idClient", sale.getClient().getId());
	    parameters.put("total", sale.getTotal());
	    int id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
	    sale.setId(id);
	    return sale;
	}
}
