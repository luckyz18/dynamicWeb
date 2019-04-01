package com.service.impl;

import java.io.InputStream;
import java.util.List;

import com.dao.CategoryDao;
import com.dao.impl.CategoryDaoImpl;
import com.domain.Category;
import com.service.CategoryService;
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
		InputStream is = CategoryServlet.class.getClassLoader().getResourceAsStream("ehcache.xml"); //流
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

}
