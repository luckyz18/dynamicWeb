package com.proxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/login")
public class EncodingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//动态代理 对request 进行增强
		//动态代理对象
		HttpServletRequest requesyProxy = (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				String m = request.getMethod();
				//需要增强的方法进行增强
				if ("getParameter".equals(method.getName())) {
					//get
					if ("get".equalsIgnoreCase(m)) {
						String s = (String) method.invoke(request, args);    //相当于 request.getParameter("")
						s = new String(s.getBytes("iso8859-1"),"utf-8");
						System.out.println("s="+s);
						return s;
					}else if ("post".equalsIgnoreCase(m)) {
						//post
						request.setCharacterEncoding("utf-8");
						return method.invoke(request, args);
					}
					
				}
				//不需要增强的方法 调用原来的方法
				return method.invoke(request, args);
			}
		});
		
		//放行
		chain.doFilter(requesyProxy, response);   //这里要传代理对象
	}
	
	@Override
	public void destroy() {
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
