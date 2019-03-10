package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Product;
import com.service.ProductService;

/**
 *  多条件查询
 */
public class FindProductByConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		
		//接收参数
		String name = request.getParameter("name");
		String kw = request.getParameter("kw");
		System.out.println(name+"---"+kw);
		
		//调service
		List<Product> plist = null;
		try {
			plist = new ProductService().findProductByCondition(name,kw);
//			System.out.println(plist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("list", plist);
		//页面跳转
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
