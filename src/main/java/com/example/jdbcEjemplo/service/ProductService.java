package com.example.jdbcEjemplo.service;

import java.util.List;

import com.example.jdbcEjemplo.dto.ProductDTO;
import com.example.jdbcEjemplo.model.Product;

public interface ProductService {
	public List<Product> getProducts();
	public Product createProduct(ProductDTO productDto);
	public Product getProductById(String id);
}
