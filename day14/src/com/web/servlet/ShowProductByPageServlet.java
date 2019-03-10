package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.PageBean;
import com.domain.Product;
import com.service.ProductService;

/**
 * 分页展示商品
 */
public class ShowProductByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//设置每页显示的条数
		int pageSize = 3;
		
		//调用service  返回值：pageBean
		PageBean<Product> bean = null;
		try {
			bean = new ProductService().showProductByPage(currentPage,pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("pb", bean);
		request.getRequestDispatcher("/product_page.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
