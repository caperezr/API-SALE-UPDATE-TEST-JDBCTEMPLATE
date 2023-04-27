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

import com.example.jdbcEjemplo.model.Product;
import com.example.jdbcEjemplo.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Product> getProducts() {
		String sql = "SELECT * FROM Product";
		List<Product> products = jdbcTemplate.query(sql, 
				(rs, rowNum) -> mapToProduct(rs));
		return products;
	}
	
	public Product mapToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("description"),
                rs.getDouble("cost"));
	}

	@Override
	public Product createProduct(Product product) {
		SimpleJdbcInsert simpleJdbcInsert =
	            new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("Product")
                .usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("description", product.getDescription());
	    parameters.put("cost", product.getCost());
	    int id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
	    product.setId(id);
	    return product;
	}

	@Override
	public Product getProductById(String id) {
		String sql = "SELECT * FROM Product WHERE id = ?";
		Product product = jdbcTemplate.queryForObject(sql, 
        		(rs, rowNum) -> mapToProduct(rs), id);
        return product;
	}



}
