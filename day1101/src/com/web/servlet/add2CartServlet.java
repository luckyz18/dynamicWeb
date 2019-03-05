package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *	 添加到购物车
 */
public class add2CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter w = response.getWriter();
		//获取商品名称
		String name = request.getParameter("name");
		
		name = new String(name.getBytes("iso8859-1"), "utf-8");
		System.out.println("name--"+name);
		//获取购物车
		Integer count=null;
		Map<String, Integer> map = (Map<String, Integer>) request.getSession().getAttribute("cart");
		if (map == null) {
			map = new HashMap();
			
			request.getSession().setAttribute("cart", map);
			count=1;
		}else {
			//有无商品
			count = map.get(name);
			if (count == null) {
				count=1;
			}else {
				count++;
			}
			
		}
		//商品放入map
		map.put(name, count);
		
		
		//提示信息
		w.println("已经将"+name+"添加到购物车");
		w.println("<a href='"+request.getContextPath()+"/product_list.jsp'>继续购物</a>&nbsp&nbsp&nbsp&nbsp&nbsp");
		
		w.println("<a href='"+request.getContextPath()+"/cart.jsp'>查看购物车</a>&nbsp&nbsp&nbsp&nbsp&nbsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
