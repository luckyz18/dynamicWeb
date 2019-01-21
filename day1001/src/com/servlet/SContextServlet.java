package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ServletContext 方法总结
 */
public class SContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = this.getServletContext();
		
		//获取初始化参数
		String encoding = context.getInitParameter("encoding");
		System.out.println("获取指定参数的初始化值："+encoding);
		
		//获取路径
		String realPath = context.getRealPath("/");
//		String realPath = context.getRealPath("/1.txt");
		System.out.println("路径是："+realPath);
		
		//以流的形式返回一个文件
		InputStream is = context.getResourceAsStream("/2.txt");
		System.out.println(is);
		
		//获取文件类型
		String mimeType = context.getMimeType("1.jpg");
		System.out.println(mimeType);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
