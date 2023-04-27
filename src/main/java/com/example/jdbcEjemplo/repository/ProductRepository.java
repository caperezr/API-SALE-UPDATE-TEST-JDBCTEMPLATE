package com.example.jdbcEjemplo.repository;

import java.util.List;

import com.example.jdbcEjemplo.model.Product;

public interface ProductRepository {
	public List<Product> getProducts();
	public Product createProduct(Product product);
	public Product getProductById(String id);
}
