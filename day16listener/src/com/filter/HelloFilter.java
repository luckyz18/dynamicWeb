package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter 拦截请求");
		
		//放行     chain过滤链，按照xml里的  一次执行里边的dofilter()方法
		chain.doFilter(request, response); 
		System.out.println("filter 接收到响应");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
}
