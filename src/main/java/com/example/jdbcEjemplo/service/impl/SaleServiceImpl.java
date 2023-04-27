package com.example.jdbcEjemplo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jdbcEjemplo.dto.SaleDTO;
import com.example.jdbcEjemplo.dto.SaleDetailDTO;
import com.example.jdbcEjemplo.model.Product;
import com.example.jdbcEjemplo.model.Sale;
import com.example.jdbcEjemplo.model.SaleDetail;
import com.example.jdbcEjemplo.repository.ClientRepository;
import com.example.jdbcEjemplo.repository.ProductRepository;
import com.example.jdbcEjemplo.repository.SaleDetailRepository;
import com.example.jdbcEjemplo.repository.SaleRepository;
import com.example.jdbcEjemplo.service.SaleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SaleServiceImpl implements SaleService{
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private SaleDetailRepository saleDetailRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductRepository productRepository;


	@Override
	public List<Sale> getSales() {
		List<Sale> sales = saleRepository.getSales();
		for(Sale sale : sales) {
			List<SaleDetail> saleDetails = saleDetailRepository
					.getSaleDetailsBySaleId(String.valueOf(sale.getId()));
			for(SaleDetail saleDetail : saleDetails) {
				saleDetail.setProduct(
						productRepository.getProductById(
								String.valueOf(saleDetail.getProduct().getId()))
						);
			}
			sale.setSaleDetails(saleDetails);
			sale.setClient(clientRepository.getClientById(String.valueOf(sale.getClient().getId())));
		}
		return sales;
	}

	@Override
	public Sale createSale(SaleDTO saleDTO) {
		try {
			
			List<SaleDetail> saleDetails = new ArrayList<>();
			for(SaleDetailDTO saleDetailDTO : saleDTO.getSaleDetailsDTO()) {
				Product product = productRepository.getProductById(String.valueOf(
						saleDetailDTO.getIdProduct()));
				SaleDetail saleDetail = new SaleDetail(saleDetailDTO, product);
				saleDetails.add(saleDetail);
			}
			
			Sale sale = new Sale(saleDTO, saleDetails);
			sale = saleRepository.createSale(sale);
			
			for(SaleDetail saleDetail : saleDetails) {
				saleDetailRepository.createSaleDetail(String.valueOf(sale.getId()), saleDetail);
			}
			sale.setSaleDetails(saleDetails);
			return sale;
		}
		catch(Exception ex) {
			log.error(ex.getMessage());
			throw ex;
		}
	}

}
