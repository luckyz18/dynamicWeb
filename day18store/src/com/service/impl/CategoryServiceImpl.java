package com.service.impl;

import java.io.InputStream;
import java.util.List;

import com.dao.CategoryDao;
import com.dao.ProductDao;
import com.dao.impl.CategoryDaoImpl;
import com.domain.Category;
import com.service.CategoryService;
import com.utils.BeanFactory;
import com.utils.DataSourceUtils;
import com.web.servlet.CategoryServlet;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CategoryServiceImpl implements CategoryService {

	/**
	 * 查找所有分类
	 * @throws Exception 
	 */
	@Override
	public List<Category> findAll() throws Exception {
		//1.获取缓存管理器
		InputStream is = CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"); //流
		CacheManager cm = CacheManager.create(is);
		
		//2.读取缓存   cache看成map
		Cache cache = cm.getCache("categoryCache");
		
		//3.通过缓存读取数据
		Element element = cache.get("clist");
		
		List<Category> list = null;
		//4.判断数据
		if (element == null) {
			//若没有 查询数据库 放入缓存
			CategoryDao dao = new CategoryDaoImpl();
			list = dao.findAll();
			cache.put(new Element("clist", list));
			System.out.println("缓存没有分类信息，从数据库读取");
			
		}else {
			//直接返回
			list = (List<Category>) element.getObjectValue();
			System.out.println("从缓存读取");
		}
				
		return list;
		
		
	}

	/**
	 * 添加分类
	 */
	@Override
	public void add(Category category) throws Exception {
		CategoryDao dao  = (CategoryDao) BeanFactory.getbean("CategoryDao");
		dao.add(category);
		
		//因为缓存中有数据 所以前台页面不会查找数据库
		//更新缓存
		//获取缓存管理器
		CacheManager cm =CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		
		//获取缓存
		Cache cache = cm.getCache("categoryCache");
		
		//清空缓存
		cache.remove("clist");
		
	}

	/**
	 * 通过id 获取一个分类
	 * @throws Exception 
	 */
	@Override
	public Category getById(String cid) throws Exception {
		CategoryDao dao = (CategoryDao) BeanFactory.getbean("CategoryDao");
		return dao.getById(cid);
		
	}

	/**
	 * 更新分类信息
	 */
	@Override
	public void update(Category category) throws Exception {
		CategoryDao dao = (CategoryDao) BeanFactory.getbean("CategoryDao");
		dao.update(category);
		
		//获取缓存管理器
		CacheManager cm =CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		//获取缓存
		Cache cache = cm.getCache("categoryCache");
		//清空缓存
		cache.remove("clist");
		
		
		
	}

	/**
	 * 删除某个分类  事务控制
	 * @throws Exception 
	 */
	@Override
	public void delete(String cid) throws Exception {
		
		try {
			//开启事务
			DataSourceUtils.startTransaction();
			//处理不同的表 用不同的dao
			ProductDao pdao = (ProductDao) BeanFactory.getbean("ProductDao");
			pdao.updateProductByCid(cid);
			
			CategoryDao cdao = (CategoryDao) BeanFactory.getbean("CategoryDao");
			cdao.delete(cid);
			
			
//			dao.updateProductByCid(cid);
//			dao.delete(cid);
			//事务处理
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	
		//清空缓存
		CacheManager cm = CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = cm.getCache("categoryCache");
		cache.remove("clist");
	}

	

}
