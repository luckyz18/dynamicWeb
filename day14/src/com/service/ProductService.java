package com.service;

import java.util.List;
import java.util.Map;

import com.dao.ProductDao;
import com.domain.PageBean;
import com.domain.Product;

public class ProductService {

	/**
	 * 	查找所有商品
	 * @return
	 * @throws Exception
	 */
	public List<Product> findAllProduct() throws Exception {
		return new ProductDao().findAllProduct();
	}

	/**
	 * 	添加商品
	 * @param p
	 * @throws Exception 
	 */
	public void addProduct(Product p) throws Exception {
		new ProductDao().addProduct(p);
		
	}
	
	/**
	 * 通过Pid获取整个商品信息
	 * @param string
	 * @return
	 * @throws Exception 
	 */
	public Product getProductById(String pid) throws Exception {
		
		return new ProductDao().getProductById(pid);
	}

	/**
	 * 修改商品
	 * @param p
	 * @throws Exception 
	 */
	public void editProduct(Product p) throws Exception {
		new ProductDao().editProduct(p);
		
	}

	/**
	 * 删除商品
	 * @param pid
	 * @throws Exception 
	 */
	public void deleteProductById(String pid) throws Exception {
		new ProductDao().deleteProductById(pid);
		
	}

	/**
	 * 删除所选商品
	 * @param pids
	 * @throws Exception 
	 */
	public void delCheckedProduct(String[] pids) throws Exception {
		ProductDao pDao = new ProductDao();
		for (String pid : pids) {
			pDao.delCheckedProduct(pid);
		}
	}

	/**
	 * 根据条件查询商品 
	 * @param name 商品名称
	 * @param kw  关键字
	 * @return
	 * @throws Exception 
	 */
	public List<Product> findProductByCondition(String name, String kw) throws Exception {
		
		return new ProductDao().findProductByCondition(name,kw);
	}

	/**
	 * 分页显示
	 * @param currentPage 
	 * @param pageSize
	 * @return pageBean
	 * @throws Exception 
	 */
	

	public PageBean<Product> showProductByPage(int currentPage, int pageSize) throws Exception {
		//查询当前页数据
		ProductDao dao = new ProductDao();
		List< Product> list = dao.findProductByPage(currentPage,pageSize);
		
		
		//查询总条数
		int totalCount = dao.getCount();
		
		return new PageBean<>(list, currentPage, pageSize, totalCount);
	}

	


}
