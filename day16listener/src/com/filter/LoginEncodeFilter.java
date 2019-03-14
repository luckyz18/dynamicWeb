package com.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang.ObjectUtils.Null;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;


public class LoginEncodeFilter implements Filter{
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 统一处理编码   装饰者模式
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//  放行     将包装过的request传递
		chain.doFilter(new Myrequest(request), response);
		
	}

	@Override
	public void destroy() {
		
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
}

class Myrequest extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	private boolean flag = true;

	public Myrequest(HttpServletRequest request) {
		super(request);
		this.request  = request;
	}
		
	
	@Override
	public String getParameter(String name) {
		if (name == null && name.trim().length()==0) {
			return null;
		}
		String[] values = getParameterValues(name);
		if (values == null && values.length==0) {
			return null;
		}
		return values[0];
	}
	
	@Override
	public String[] getParameterValues(String name) {
		if (name == null && name.trim().length()==0) {
			return null;
		}
		Map<String, String[]> map = getParameterMap();
		if (map == null && map.size()==0) {
			return null;
		}
		return map.get(name);
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		String m = request.getMethod();
		if ("post".equalsIgnoreCase(m)) {
			try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return request.getParameterMap();
			
		}else if ("get".equalsIgnoreCase(m)) {
			Map<String, String[]> map = request.getParameterMap();
			
			for (String key : map.keySet()) {
				String[] arr = map.get(key);
				for(int i=0;i < arr.length;i++) {
					try {
						arr[i] =new String(arr[i].getBytes("iso8859-1"),"utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
			
			flag= false;   //避免重复编 码  ？
		
		}
		return super.getParameterMap();
	}
}















