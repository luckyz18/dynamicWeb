package com.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LifeServlet implements Servlet {

	/**
	 * 初始化方法
	 * 执行者：服务器
	 * 执行次数：1
	 * 执行时机：默认 第一次访问时
	 */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("5555555");

	}

	/**
	 * 服务方法
	 * 执行者：服务器
	 * 执行次数：请求一次 执行一次
	 * 执行时机：请求来的时候
	 */
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("servive66666666");

	}
	
	/**
	 * 销毁方法
	 * 执行者：服务器
	 * 执行次数：只销毁一次
	 * 执行时机：当servlet被移除 或者服务器正常关闭的时候
	 */
	@Override
	public void destroy() {
		System.out.println("44444444");

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
