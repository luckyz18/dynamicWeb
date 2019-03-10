package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.domain.Product;
import com.service.ProductService;

/**
 * 	修改商品
 */
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
			
			try {
				
				Product p = new Product();
				BeanUtils.populate(p,request.getParameterMap());
				new ProductService().editProduct(p);
				
				response.sendRedirect(request.getContextPath()+"/findAll");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "修改失败");
				response.sendRedirect(request.getContextPath()+"/msg.jsp");
			}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
