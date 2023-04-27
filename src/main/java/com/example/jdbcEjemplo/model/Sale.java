package com.example.jdbcEjemplo.model;

import java.util.List;

import com.example.jdbcEjemplo.dto.SaleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
	private int id;
	private Client client;
	private double total;
	private List<SaleDetail> saleDetails;
	
	public Sale(SaleDTO saleDTO, List<SaleDetail> saleDetails) {
		Client client = new Client();
		client.setId(saleDTO.getIdClient());

		this.client = client;
		double total = 0.0;
		for(int i = 0; i < saleDetails.size(); i++) {
			total+= saleDTO.getSaleDetailsDTO().get(i).getQuantity() * 
					saleDetails.get(i).getProduct().getCost();
		}
		this.total = total;
	}
}
