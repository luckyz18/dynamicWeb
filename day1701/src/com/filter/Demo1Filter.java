package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/demo1")
public class Demo1Filter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("demo1Filter执行-----");
		chain.doFilter(request, response);
	}
   

	
	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
