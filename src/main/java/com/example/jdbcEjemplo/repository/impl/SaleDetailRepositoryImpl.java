package com.example.jdbcEjemplo.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.jdbcEjemplo.model.Product;
import com.example.jdbcEjemplo.model.SaleDetail;
import com.example.jdbcEjemplo.repository.SaleDetailRepository;

@Repository
public class SaleDetailRepositoryImpl implements SaleDetailRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<SaleDetail> getSaleDetailsBySaleId(String saleId) {
		String sql = "SELECT * FROM SALEDETAIL WHERE idSale = ?";
        List<SaleDetail> listSaleDetails = jdbcTemplate.query(sql, 
        		(rs, rowNum) -> mapToSaleDetail(rs), saleId);
        return listSaleDetails;
	}
	
	public SaleDetail mapToSaleDetail(ResultSet rs) throws SQLException {
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(rs.getInt("id"));
        Product product = new Product();
        product.setId(rs.getInt("idProduct"));
        saleDetail.setQuantity(rs.getInt("quantity"));
        saleDetail.setSubtotal(rs.getDouble("subtotal"));
        saleDetail.setProduct(product);
        
		return saleDetail;
	}

	@Override
	public SaleDetail createSaleDetail(String idSale, SaleDetail saleDetail) {
		SimpleJdbcInsert simpleJdbcInsert =
	            new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("SALEDETAIL")
                .usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("idSale", idSale);
	    parameters.put("idProduct", saleDetail.getProduct().getId());
	    parameters.put("quantity", saleDetail.getQuantity());
	    parameters.put("subtotal", saleDetail.getSubtotal());
	    int id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
	    saleDetail.setId(id);
	    return saleDetail;
	}
}
