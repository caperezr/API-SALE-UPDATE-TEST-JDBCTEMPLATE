package com.example.jdbcEjemplo.repository;

import com.example.jdbcEjemplo.model.Product;
import com.example.jdbcEjemplo.model.SaleDetail;
import com.example.jdbcEjemplo.repository.impl.SaleDetailRepositoryImpl;
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
public class SaleDetailRepositoryTest {
    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    private SaleDetailRepositoryImpl saleDetailRepositoryImpl;

    @Test
    void getSaleDetailsBySaleIdTest() {
        String saleId = "1";
        Product product = new Product(1, "Manzana", 1.0);
        SaleDetail saleDetail1 = new SaleDetail(1, product, 10, 20);
        SaleDetail saleDetail2 = new SaleDetail(2, product, 4, 20);
        List<SaleDetail> expectedSaleDetails = new ArrayList<>();
        expectedSaleDetails.add(saleDetail1);
        expectedSaleDetails.add(saleDetail2);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), anyString())).thenReturn(expectedSaleDetails);
        List<SaleDetail> actualSaleDetails = saleDetailRepositoryImpl.getSaleDetailsBySaleId(saleId);
        assertEquals(expectedSaleDetails.size(), actualSaleDetails.size());
        assertEquals(expectedSaleDetails, actualSaleDetails);
    }

    @Test
    void mapToSaleDetailTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        Product product = new Product(1, "Manzana", 1.0);
        SaleDetail expectedSaleDetail1 = new SaleDetail(1, product, 10, 20);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("idProduct")).thenReturn(1);
        when(resultSet.getInt("quantity")).thenReturn(10);
        when(resultSet.getDouble("subtotal")).thenReturn(20.0);
        SaleDetail actualSaleDetail = saleDetailRepositoryImpl.mapToSaleDetail(resultSet);
        assertNotNull(actualSaleDetail);
        assertEquals(actualSaleDetail.getId(), expectedSaleDetail1.getId());
        assertEquals(actualSaleDetail.getProduct().getId(), expectedSaleDetail1.getProduct().getId());
        assertEquals(actualSaleDetail.getQuantity(),expectedSaleDetail1.getQuantity());
        assertEquals(actualSaleDetail.getSubtotal(), expectedSaleDetail1.getSubtotal());


    }

}
