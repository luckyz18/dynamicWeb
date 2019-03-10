package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Product;
import com.service.ProductService;

/**
 * 	通过id获取一个商品
 */
public class GetProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		//查询该商品 返回Product
		try {
			Product p = new ProductService().getProductById(pid);
			System.out.println(p.getPname()+"-----");		
			
			request.setAttribute("bean", p);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
