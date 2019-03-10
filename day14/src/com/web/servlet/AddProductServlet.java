package com.web.servlet;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import com.domain.Product;
import com.service.ProductService;
import com.utils.UUIdUtils;

/**
 * 	添加商品
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
			try {
				//扩展令牌机制      或者使用重定向
				String s_token = (String) request.getSession().getAttribute("s_token");
				String r_token = request.getParameter("r_token");
				
				System.out.println("s_token:"+s_token+"r_token:"+r_token);
				
				//删除session
				request.getSession().removeAttribute("s_token");
				
				//比较令牌
				if (s_token==null || !r_token.equals(s_token)) {
					request.setAttribute("msg", "已经添加过该商品 不可重复添加");
					request.getRequestDispatcher("/msg.jsp").forward(request, response);
					return;  //后面不再执行
					
				}
				
				Product p =new Product();
				BeanUtils.populate(p,request.getParameterMap());
				//设置pid
				p.setPid(UUIdUtils.getId());
				//设置时间
				p.setPdate(new Date());
				//service 操作
				new ProductService().addProduct(p);
				
				//请求转发
				request.getRequestDispatcher("/findAll").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "添加失败");
				request.getRequestDispatcher("/msg.jsp").forward(request, response);
			}
			
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
