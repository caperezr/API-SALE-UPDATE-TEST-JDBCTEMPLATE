package com.example.jdbcEjemplo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.jdbcEjemplo.dto.ProductDTO;
import com.example.jdbcEjemplo.model.Product;
import com.example.jdbcEjemplo.repository.ProductRepository;
import com.example.jdbcEjemplo.service.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;

	@Test
	void getProductsTest() {
		Product product = new Product(1, "gaseosa", 10.5);
		Product product2 = new Product(2, "galleta", 0.5);
		List<Product> expectedProducts = new ArrayList<>();
		expectedProducts.add(product);
		expectedProducts.add(product2);

		when(productRepository.getProducts()).thenReturn(expectedProducts);

		List<Product> actualProducts = productServiceImpl.getProducts();
		assertEquals(expectedProducts.size(), actualProducts.size());
		assertEquals(expectedProducts, actualProducts);
	}

	@Test
	void getProductByIdTest() {
		String id = "1";
		Product expectedProduct = new Product(1, "gaseosa", 10.5);

		when(productRepository.getProductById(id)).thenReturn(expectedProduct);

		Product actualProduct = productServiceImpl.getProductById(id);

		assertNotNull(actualProduct);
		assertEquals(actualProduct.getDescription(), expectedProduct.getDescription());
		assertEquals(actualProduct.getCost(), expectedProduct.getCost());
	}

	@Test
	void createProductTest() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setDescription("gaseosa");
		productDTO.setCost(10.5);

		Product expectedProduct = new Product();
		expectedProduct.setDescription("gaseosa");
		expectedProduct.setCost(10.5);

		when(productRepository.createProduct(any(Product.class))).thenReturn(expectedProduct);

		Product actualProduct = productServiceImpl.createProduct(productDTO);

		assertNotNull(actualProduct);
		assertEquals(actualProduct.getDescription(), expectedProduct.getDescription());
		assertEquals(actualProduct.getCost(), expectedProduct.getCost());
	}
}
