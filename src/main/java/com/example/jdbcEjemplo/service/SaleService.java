package com.example.jdbcEjemplo.service;

import java.util.List;

import com.example.jdbcEjemplo.dto.SaleDTO;
import com.example.jdbcEjemplo.model.Sale;

public interface SaleService {
	public List<Sale> getSales();
	public Sale createSale(SaleDTO saleDTO);
}
