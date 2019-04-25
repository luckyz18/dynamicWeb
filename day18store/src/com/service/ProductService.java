package com.service;

import java.sql.SQLException;
import java.util.List;

import com.domain.Pagebean;
import com.domain.Product;

public interface ProductService {

	List<Product> findNew()throws Exception;

	List<Product> findHot()throws Exception;

	Product getByPid(String pid) throws SQLException;

	Pagebean<Product> findByPage(String cid, int currPage, int pageSize) throws Exception;

	List<Product> findAll() throws Exception;

	Pagebean<Product> findAllByPage(int currPage, int pageSize) throws Exception;

	void add(Product p) throws Exception;

	List<Product> findAllByStopSell()throws Exception;

	void stopSellByPid(String pid)throws Exception;

//	void update(Product p)throws Exception;

	

}
