package com.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.management.Query;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.ProductDao;
import com.domain.Category;
import com.domain.Product;
import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;
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

	/**
	 * 删除分类前 先删除商品中这个分类信息   外键
	 * @throws Exception 
	 */
	@Override
	public void updateProductByCid(String cid) throws Exception {
		QueryRunner qr = new QueryRunner();   //事务 无参
		String sql = "update product set cid = null  where cid = ? ";
		qr.update(DataSourceUtils.getConnection(), sql, cid);
		
	}

	/**
	 * 查询所有商品
	 * @throws Exception 
	 */
	@Override
	public List<Product> findAll() throws Exception {
		// TODO 查询所有商品
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product  where pflag= 0 ";
		return qr.query(sql, new BeanListHandler<>(Product.class));
		
	}
	
	/**
	 * 查询所有已下架商品
	 * @throws Exception 
	 */
	@Override
	public List<Product> findAllByStopSell() throws Exception {
		// TODO 查询所有已下架商品
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pflag= 1 ";
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}

	/**
	 * test 分页查询 返回商品list
	 * @throws Exception 
	 */
	@Override
	public List<Product> findAllByPage(int currPage, int pageSize) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product limit ?,? ";
		return qr.query(sql, new BeanListHandler<>(Product.class), (currPage-1)*pageSize,pageSize);
	}

	/**
	 * test 分页  总商品数
	 * @throws Exception 
	 */
	@Override
	public int findTotalCount() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product ";
		return ((Long)qr.query(sql,new ScalarHandler())).intValue();
		
	}

	/**
	 * 添加商品
	 * @throws Exception 
	 */
	@Override
	public void add(Product p) throws Exception {
		// TODO 添加商品
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,? )";
		qr.update(sql, p.getPid(),p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPimage(),p.getPdate(),p.getIs_hot(),
				p.getPdesc(),p.getPflag(),p.getCategory().getCid());
				
		
	}

	/**
	 * 修改已下架商品的下架状态
	 */
	@Override
	public void stopSellByPid(String  pid) throws Exception {
		// TODO 修改已下架商品的下架状态
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update product set pflag = 1 where pid = ? ";
		qr.update(sql, pid);
	}

	

	/**
	 * 更新商品信息
	 */
//	@Override
	/*public void updateProduc(Product p) throws Exception {
		// TODO 更新商品信息
		*//**
		 * private String pid;
			private String pname;
			private double market_price;
			
			private double shop_price;
			private String pimage;
			private Date pdate;
			
			private Integer is_hot;  //1热门 0不热门
			private String pdesc;
			private Integer pflag;  //1下架 0未下架
			
			private Category category;  //属于哪一个分类
		 *//*
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update product set pid=?, pname=?, market_price=?, shop_price=?, pimage=?, pdate=?, is_hot=?, pdesc=?, pflag=?, cid=? ";
		qr.update(sql, p.getPid(),p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPimage(),p.getPdate(),p.getIs_hot(),p.getPdesc(),
				p.getPflag(),p.getCategory().getCid()
				);
		
		
	}*/
}
