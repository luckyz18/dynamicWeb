package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.domain.Product;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.utils.DataSourceUtils;

public class ProductDao {

	/**
	 * 	查找所有商品
	 * @return
	 * @throws Exception
	 */
	public List<Product> findAllProduct() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product";
		List<Product> list = qr.query(sql,new BeanListHandler<>(Product.class));
		return list;
	}

	/**
	 * 	添加商品
	 * @param p
	 * @throws Exception 
	 */
		/*
		`pid` varchar (96),
		`pname` varchar (150),
		`market_price` double ,
		`shop_price` double ,
		`pimage` varchar (600),
		`pdate` date ,
		`pdesc` varchar (765)
		*/
	public void addProduct(Product p) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product(pid,pname,market_price,shop_price,pdate,pdesc) values(?,?,?,?,?,?)";
		qr.update(sql, p.getPid(),p.getPname(),p.getMarket_price(), p.getShop_price(),p.getPdate(),p.getPdesc());
		
	}

	/**
	 * 通过Pid获取整个商品信息
	 * @param pid
	 * @return
	 * @throws Exception 
	 */
	public Product getProductById(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from Product where pid=?";
		return qr.query(sql,new BeanHandler<>(Product.class),pid);		
	}

	/**
	 * 修改商品
	 * @param p
	 * @throws Exception 
	 */
	public void editProduct(Product p) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update product set pname=?,market_price=?,shop_price=?,pdesc=? where pid=?";
		qr.update(sql,p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPdesc(),p.getPid());
	}

	/**
	 * 删除商品
	 * @param pid
	 * @throws Exception 
	 */
	public void deleteProductById(String pid) throws Exception {
		QueryRunner  qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product where pid=?";
		qr.update(sql, pid);
	}

	/**
	 * 删除所选商品
	 * @param pids
	 * @throws Exception 
	 */
	public void delCheckedProduct(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql= "delete from product where pid = ?";
		qr.update(sql, pid);
		
	}

	/**
	 * 多条件查询
	 * @param name
	 * @param kw
	 * @return List
	 * @throws Exception 
	 */
	public List<Product> findProductByCondition(String name, String kw) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where 1=1 ";
		
		//存放参数  可变长度
		ArrayList< String> params = new ArrayList<>();
		
		//若商品名称不为空
		if (name!=null && name.trim().length()>0) {
			sql+=" and pname like ? ";
			params.add("%"+name+"%");
		}
		//若关键字不为空
		if (kw!=null && kw.trim().length()>0) {
			sql+=" and pdesc like ? ";
			params.add("%"+kw+"%");
		}

//		System.out.println( qr.query(sql, new BeanListHandler<>(Product.class), params.toArray()));
		return qr.query(sql, new BeanListHandler<>(Product.class), params.toArray());   //参数需要转化成数组
		
	}

	/**
	 * 查询总条数
	 * @return  int 
	 * @throws Exception 
	 */
	public int getCount() throws Exception {
		QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product";
		return ((Long)qr.query(sql, new ScalarHandler())).intValue();      //返回的是一个object对象  强转成Long 
		 
	}

	/**
	 * 查找当前页商品 limit 
	 * @param currentPage
	 * @param pageSize
	 * @return 商品list
	 * @throws Exception 
	 */
	public List<Product> findProductByPage(int currentPage, int pageSize) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product limit ?,?";
		return qr.query(sql, new BeanListHandler<>(Product.class), (currentPage-1)*pageSize,pageSize);
		
	}

	
	
}
