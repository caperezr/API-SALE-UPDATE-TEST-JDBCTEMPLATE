package com.example.jdbcEjemplo.repository;

import com.example.jdbcEjemplo.model.Product;
import com.example.jdbcEjemplo.repository.impl.ProductRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
	@Mock
	JdbcTemplate jdbcTemplate;

	@InjectMocks
	private ProductRepositoryImpl productRepositoryImpl;

	@Test
	void getProductsTest() {
		Product product1 = new Product(1, "Manzana", 1.0);
		Product product2 = new Product(2, "Pera", 1.3);
		List<Product> expectedProducts = new ArrayList<>();
		expectedProducts.add(product1);
		expectedProducts.add(product2);
		when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedProducts);
		List<Product> actualProducts = productRepositoryImpl.getProducts();
		assertEquals(expectedProducts.size(), actualProducts.size());
		assertEquals(expectedProducts, actualProducts);
	}

	@Test
	void getProductByIdTest() {
		String id = "1";
		Product expectedProduct = new Product(1, "Manzana", 1.0);
		when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn(expectedProduct);
		Product actualProduct = productRepositoryImpl.getProductById(id);
		assertNotNull(actualProduct);
		assertEquals(actualProduct.getDescription(), expectedProduct.getDescription());
		assertEquals(actualProduct.getCost(), expectedProduct.getCost());
	}

	@Test
	void mapToProductTest() throws SQLException {
		ResultSet resultSet = mock(ResultSet.class);
		Product expectedProduct = new Product(1, "Manzana", 1.0);
		when(resultSet.getInt("id")).thenReturn(1);
		when(resultSet.getString("description")).thenReturn("Manzana");
		when(resultSet.getDouble("cost")).thenReturn(1.0);
		Product actualProduct = productRepositoryImpl.mapToProduct(resultSet);
		assertNotNull(actualProduct);
		assertEquals(actualProduct.getId(), expectedProduct.getId());
		assertEquals(actualProduct.getDescription(), expectedProduct.getDescription());
		assertEquals(actualProduct.getCost(), expectedProduct.getCost());
	}

}
