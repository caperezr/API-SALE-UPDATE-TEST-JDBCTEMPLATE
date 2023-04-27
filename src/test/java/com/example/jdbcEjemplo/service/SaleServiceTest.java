package com.example.jdbcEjemplo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.jdbcEjemplo.model.Client;
import com.example.jdbcEjemplo.model.Product;
import com.example.jdbcEjemplo.model.Sale;
import com.example.jdbcEjemplo.model.SaleDetail;
import com.example.jdbcEjemplo.repository.ClientRepository;
import com.example.jdbcEjemplo.repository.ProductRepository;
import com.example.jdbcEjemplo.repository.SaleDetailRepository;
import com.example.jdbcEjemplo.repository.SaleRepository;
import com.example.jdbcEjemplo.service.impl.SaleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SaleServiceTest {
    @Mock
    private SaleRepository saleRepository;

    @Mock
    private SaleDetailRepository saleDetailRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SaleServiceImpl saleServiceImpl;

    @Test
    void getSalesTest() {
        Product product1 = new Product(1, "gaseosa", 2.5);
        Product product2 = new Product(2, "galleta", 0.5);

        SaleDetail saleDetail1 = new SaleDetail(1, product1, 2, 5);
        SaleDetail saleDetail2 = new SaleDetail(2, product2, 2, 1);

        List<SaleDetail> listSaleDetails = new ArrayList<>();
        listSaleDetails.add(saleDetail1);
        listSaleDetails.add(saleDetail2);

        Client client = new Client(1, "percy", "956123982");
        Sale sale = new Sale(1, client, 6, listSaleDetails);
        List<Sale> listSales = new ArrayList<>();
        listSales.add(sale);

        when(saleRepository.getSales()).thenReturn(listSales);
        when(saleDetailRepository.getSaleDetailsBySaleId("1")).thenReturn(listSaleDetails);
        when(productRepository.getProductById("1")).thenReturn(product1);
        when(productRepository.getProductById("2")).thenReturn(product2);
        when(clientRepository.getClientById("1")).thenReturn(client);


        List<Sale> actualSales = saleServiceImpl.getSales();

        assertNotNull(actualSales);
        assertEquals(1, actualSales.size());
        assertEquals(actualSales.get(0).getClient().getName(), client.getName());
        assertEquals(actualSales.get(0).getClient().getPhone(), client.getPhone());
        assertEquals(sale.getTotal(), actualSales.get(0).getTotal());

        assertEquals(2, actualSales.get(0).getSaleDetails().size());
    }
}


