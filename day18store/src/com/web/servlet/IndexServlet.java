package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Category;
import com.domain.Product;
import com.service.CategoryService;
import com.service.ProductService;
import com.service.impl.CategoryServiceImpl;
import com.service.impl.ProductServiceImpl;

/**
 * 和首页相关的servlet
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String index(HttpServletRequest request, HttpServletResponse response) {
		//页面加载时 查询最新和热门商品
		ProductService ps = new ProductServiceImpl();
		List<Product> newList = null;
		List<Product> hotList = null;
		try {
			newList = ps.findNew();
			hotList = ps.findHot();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		
		//放入request域中  请求转发
		request.setAttribute("nList", newList);
		request.setAttribute("hList", hotList);
		
		
		return "/jsp/index.jsp";
		
	}
	
	
	
	

}
