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

	void updateProductByCid(String cid)throws  Exception;

	List<Product> findAll() throws Exception;

	List<Product> findAllByPage(int currPage, int pageSize) throws Exception;   //test 分页查询

	int findTotalCount() throws Exception;  //test 分页查询

	void add(Product p) throws Exception;

	List<Product> findAllByStopSell()throws Exception;

	void stopSellByPid(String pid)throws Exception;


}
