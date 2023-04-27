package com.example.jdbcEjemplo.repository;

import com.example.jdbcEjemplo.model.Client;
import com.example.jdbcEjemplo.model.Product;
import com.example.jdbcEjemplo.model.Sale;
import com.example.jdbcEjemplo.model.SaleDetail;
import com.example.jdbcEjemplo.repository.impl.SaleRepositoryImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaleRepositoryTest {
    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    SaleRepositoryImpl saleRepositoryImpl;

    @Test
    void getSalesTest() {
        Client client = new Client(1, "Cristhian", "999999999");
        Product product = new Product(1, "Manzana", 1.0);
        SaleDetail saleDetail1 = new SaleDetail(1, product, 10, 20);
        SaleDetail saleDetail2 = new SaleDetail(2, product, 4, 20);
        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(saleDetail1);
        saleDetails.add(saleDetail2);
        Sale sale1 = new Sale(1, client, 20, saleDetails);
        Sale sale2 = new Sale(2, client, 10, saleDetails);
        List<Sale> expectedSales = new ArrayList<>();
        expectedSales.add(sale1);
        expectedSales.add(sale2);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedSales);

        List<Sale> actualSales = saleRepositoryImpl.getSales();
        assertEquals(expectedSales.size(), actualSales.size());
        assertEquals(expectedSales, actualSales);

    }

    @Test
    void mapToSaleTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        Client client = new Client(1, "Cristhian", "999999999");
        Product product = new Product(1, "Manzana", 1.0);
        SaleDetail saleDetail = new SaleDetail(1, product, 10, 20);
        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(saleDetail);

        Sale expectedSale = new Sale(1, client, 20, saleDetails);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getDouble("total")).thenReturn(20.0);
        when(resultSet.getInt("idClient")).thenReturn(1);
        Sale actualSale = saleRepositoryImpl.mapToSale(resultSet);
        assertNotNull(actualSale);
        assertEquals(actualSale.getId(), expectedSale.getId());
        assertEquals(expectedSale.getId(), actualSale.getId());
        assertEquals(expectedSale.getTotal(), actualSale.getTotal());
        assertEquals(client.getId(), actualSale.getClient().getId());
    }

}
