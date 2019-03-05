package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.domain.Product;
import com.utils.DataSourceUtils;

public class ProductDao {

	public List<Product> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product";
		List<Product> bean_list = qr.query(sql, new BeanListHandler<>(Product.class));
		return bean_list;
		
	}

}
