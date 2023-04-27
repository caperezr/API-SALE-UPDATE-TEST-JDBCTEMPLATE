package com.example.jdbcEjemplo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jdbcEjemplo.dto.SaleDTO;
import com.example.jdbcEjemplo.model.Sale;
import com.example.jdbcEjemplo.service.SaleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/sales")
@Slf4j
public class SaleController {
	@Autowired
	private SaleService saleService;
	
	@GetMapping("")
	public ResponseEntity<List<Sale>> getSales(){
		try {
			List<Sale> sales = saleService.getSales();
			return ResponseEntity.status(HttpStatus.OK).body(sales);
		}
		catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	@PostMapping("")
	@Transactional
	public ResponseEntity<Sale> createSale(@RequestBody SaleDTO saleDto){
		try {
			Sale sale = saleService.createSale(saleDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(sale);
		}
		catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
}
