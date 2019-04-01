package com.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Category;
import com.service.CategoryService;
import com.service.impl.CategoryServiceImpl;
import com.utils.BeanFactory;
import com.utils.JsonUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;

/**
 * 查找所有分类
 */
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

   
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// 1.调用categoryservice 查询所有的分类 返回值list
//		CategoryService cs = new CategoryServiceImpl();
		CategoryService cs = (CategoryService) BeanFactory.getbean("CategoryService");
		List<Category> clist = null;
		try {
			clist = cs.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2.将返回值转成json格式 返回到页面上
		//request.setAttribute("clist", clist);
		String json = JsonUtil.list2json(clist);
		
		//3.写回去
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json);
		
		return null;
	}
}
