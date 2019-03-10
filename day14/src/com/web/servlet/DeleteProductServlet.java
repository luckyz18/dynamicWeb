package com.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.service.ProductService;

/**
 * 删除商品
 */
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
//		System.out.println(pid);
		
		//service
		try {
			new ProductService().deleteProductById(pid);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除失败");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			return;
		}
		//重定向 到 findAll
		response.sendRedirect(request.getContextPath()+"/findAll");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
