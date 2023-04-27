package com.example.jdbcEjemplo.service.impl;

import java.util.List;

import com.example.jdbcEjemplo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jdbcEjemplo.dto.ProductDTO;
import com.example.jdbcEjemplo.model.Product;
import com.example.jdbcEjemplo.repository.ProductRepository;
import com.example.jdbcEjemplo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;


	
	@Override
	public List<Product> getProducts() {
		return productRepository.getProducts();
	}

	@Override
	public Product createProduct(ProductDTO productDto) {
		Product product = new Product(productDto);
		return productRepository.createProduct(product);
	}

	@Override
	public Product getProductById(String id) {
		return productRepository.getProductById(id);
	}

}
