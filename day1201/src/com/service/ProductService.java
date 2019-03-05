package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.ProductDao;
import com.domain.Product;

public class ProductService {

	public List<Product> findAll() throws SQLException {
		return new ProductDao().findAll();
		
		
	}

}
