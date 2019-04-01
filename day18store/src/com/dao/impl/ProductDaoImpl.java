package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.ProductDao;
import com.domain.Product;
import com.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {

	/**
	 * 查询热门商品
	 * @throws SQLException 
	 */
	@Override
	public List<Product> findhot() throws SQLException {
		// TODO 查询热门商品
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot = 1 limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class));
		
	}

	/**
	 * 查询最新商品
	 * @throws SQLException 
	 */
	@Override
	public List<Product> findNew() throws SQLException {
		// TODO 查询最新商品
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product order by pdate limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class));
		
	}

	/**
	 * 根据pid获取商品
	 * @throws SQLException 
	 */
	@Override
	public Product getByPid(String pid) throws SQLException {
		// TODO 根据pid获取商品
		QueryRunner qr = new QueryRunner(new DataSourceUtils().getDataSource());
		String sql = "select * from product where pid = ? limit 1 ";
		return qr.query(sql, new BeanHandler<>(Product.class), pid);
		
	}

	/**
	 * 获取当前页商品
	 */
	@Override
	public List<Product> getByPage(String cid,int currPage,int pageSize) throws SQLException {
		// TODO 获取当前页商品
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid = ? limit ?,? ";
		return qr.query(sql, new BeanListHandler<>(Product.class), cid,pageSize*(currPage-1),pageSize);
	}

	/**
	 * 获取商品总条数
	 */
	@Override
	public int getTotalCount(String cid) throws SQLException {
		// TODO 获取商品总条数
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid = ? ";
//		return (int) qr.query(sql, new ScalarHandler(), cid);
		return ((Long) qr.query(sql, new ScalarHandler(), cid)).intValue();
	}

}
