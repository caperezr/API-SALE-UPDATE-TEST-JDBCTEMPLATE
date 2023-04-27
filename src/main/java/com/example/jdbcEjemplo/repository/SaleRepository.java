package com.example.jdbcEjemplo.repository;

import java.util.List;

import com.example.jdbcEjemplo.model.Sale;

public interface SaleRepository {
	public List<Sale> getSales();
	public Sale createSale(Sale sale);
}
