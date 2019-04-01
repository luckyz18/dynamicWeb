package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.domain.Product;

public interface ProductDao {

	List<Product> findhot() throws SQLException;

	List<Product> findNew() throws SQLException;

	Product getByPid(String pid) throws SQLException ;

	List<Product>getByPage(String cid,int currPage,int pageSize)throws SQLException;

	int getTotalCount(String cid) throws SQLException;

}
