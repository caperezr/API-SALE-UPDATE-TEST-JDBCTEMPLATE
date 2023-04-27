package com.example.jdbcEjemplo.repository;

import java.util.List;

import com.example.jdbcEjemplo.model.SaleDetail;

public interface SaleDetailRepository {
	public List<SaleDetail> getSaleDetailsBySaleId(String saleId);
	public SaleDetail createSaleDetail(String saleId, SaleDetail saleDetail);
}
