package com.filter;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.service.UserService;
import com.utils.CookieUtils;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//拦截请求 获取cookie
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//1.session中有无登录用户 没有的话 自动登录   只需要登录一次
		User user = (User)request.getSession().getAttribute("user");
		if (user == null) {
			//2.是否 跟登录注册相关
			String path = request.getRequestURI();  // /day16/xxx
			System.out.println("path:"+path);
			
			if (!path.contains("/login")) {
				//3. 获取指定cookie
				Cookie c = CookieUtils.getCookieByName("autologin", request.getCookies());
				
				// cookie 不为空
				if (c!= null) {
					String username = c.getValue().split("-")[0];
					String password = c.getValue().split("-")[1];
					
					try {
						user = new UserService().getUserByUsernameAndPwd(username, password);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if (user != null) {
						// user  放入session 中
						request.getSession().setAttribute("user", user);
						
					}
				}
			}
		}
	
		//放行
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
