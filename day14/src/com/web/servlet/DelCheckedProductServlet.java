package com.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.ProductService;

/**
 * 删除所选商品 多个
 */
public class DelCheckedProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pids = request.getParameterValues("pid");
		
		//service
		try {
			new ProductService().delCheckedProduct(pids);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除选中多个商品失败");
			request.getRequestDispatcher("/msg.jsp");
			return;
		}
		
		//跳转 findAll
		System.out.println("删除多个选中商品成功");
		response.sendRedirect(request.getContextPath()+"/findAll");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
