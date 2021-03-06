package com.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.dao.ProductDao;
import com.dao.impl.ProductDaoImpl;
import com.domain.Category;
import com.domain.Pagebean;
import com.domain.Product;
import com.service.ProductService;
import com.utils.BeanFactory;

import net.sf.ehcache.search.aggregator.Count;

public class ProductServiceImpl implements ProductService {

	/**
	 * 查询最新商品
	 */
	@Override
	public List<Product> findNew() throws Exception {
		// TODO 查询最新商品
		ProductDao dao = new ProductDaoImpl();
		return dao.findNew();
		
	}

	/**
	 * 查询热门商品
	 */
	@Override
	public List<Product> findHot() throws Exception {
		// TODO 查询热门商品
		ProductDao dao = new ProductDaoImpl();
		return dao.findhot();
	}

	/**
	 * 根据pid获取商品
	 * @throws SQLException 
	 */
	@Override
	public Product getByPid(String pid) throws SQLException {
		// TODO 根据pid获取商品
		ProductDao dao = new ProductDaoImpl();
		return dao.getByPid(pid);
		
	}

	/**
	 * 根据分类 分页展示商品
	 */
	@Override
	public Pagebean<Product> findByPage(String cid, int currPage, int pageSize) throws Exception {
		// TODO 根据分类 分页展示商品
		
		//获取list
		ProductDao dao = new ProductDaoImpl();
		List<Product> list = dao.getByPage(cid,currPage,pageSize);

		
		//totalcount
		int totalCount = dao.getTotalCount(cid);
		
		return new Pagebean<>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 查询所有商品
	 * @throws Exception 
	 */
	@Override
	public List<Product> findAll() throws Exception {
		// TODO 查询所有商品
		ProductDao dao= (ProductDao) BeanFactory.getbean("ProductDao");
		return dao.findAll();
		
	}
	/**
	 * 查询已下架商品
	 */
	@Override
	public List<Product> findAllByStopSell() throws Exception {
		// TODO 查询已下架商品
		ProductDao dao= (ProductDao) BeanFactory.getbean("ProductDao");
		return dao.findAllByStopSell();
	}


	/**
	 * test  分页
	 * @throws Exception 
	 */
	@Override
	public Pagebean<Product> findAllByPage(int currPage, int pageSize) throws Exception {
		ProductDao dao= (ProductDao) BeanFactory.getbean("ProductDao");
		
		List<Product> list = dao.findAllByPage(currPage,pageSize);
		int totalCount = dao.findTotalCount();
		
		return new Pagebean<>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 添加商品
	 * @throws Exception 
	 */
	@Override
	public void add(Product p) throws Exception {
		ProductDao dao= (ProductDao) BeanFactory.getbean("ProductDao");
		dao.add(p);
	}

	/**
	 * 修改已下架商品的 下架状态
	 */
	@Override
	public void stopSellByPid(String pid) throws Exception {
		// TODO 修改已下架商品的 下架状态
		ProductDao dao= (ProductDao) BeanFactory.getbean("ProductDao");
		dao.stopSellByPid(pid);
	}

	

}
