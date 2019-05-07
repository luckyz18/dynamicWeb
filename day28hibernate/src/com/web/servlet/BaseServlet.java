package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取子类
			Class clazz = this.getClass();
		 	
			//获取方法
			String m = request.getParameter("method");
			if (m==null) {
				m="index";  //执行 index方法
			}
			
			//获取方法对象
			Method method = clazz.getMethod(m, HttpServletRequest.class,HttpServletResponse.class);
			
			//执行方法
			String s = (String) method.invoke(this, request,response);
			
			//是否重定向
			if (s!=null) {
				request.getRequestDispatcher(s).forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
		
		
	}
	
	//所有的servlet都有一个index方法  所以在base里边写一个index方法
	public String index (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return null;
	}
	

}
