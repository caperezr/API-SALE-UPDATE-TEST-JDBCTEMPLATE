package com.example.jdbcEjemplo.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleDTO {
	private int idClient;
	private ArrayList<SaleDetailDTO> saleDetailsDTO;
}
