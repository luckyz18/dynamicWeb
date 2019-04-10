package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;

public class PrivilegeFilter implements Filter {

	@Override
	public void destroy() {

	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//1.强转
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//2.处理业务逻辑   -- 粗粒度处理权限问题
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "没有权限 请先登录");
			request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
			return;
		}
		
		
		//3.放行
		chain.doFilter(request, response);

	}


}
