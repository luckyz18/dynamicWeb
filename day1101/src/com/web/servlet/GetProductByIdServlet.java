package com.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.CookieUtils;


public class GetProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		
		
		//判断cookie是否为空
		String id = request.getParameter("id");
		Cookie c = CookieUtils.getCookieByName("ids",request.getCookies());
		System.out.println("c:"+c);
		
		
		//若cookie为空
		String ids = "";
		if (c == null) {
			ids=id;
		}else {
			//若cookie不空  继续判断ids是否包含id  ids=1-2-3
			//获取值
			ids=c.getValue();
			//分割ids
			String[] arr = ids.split("-");
			List<String> aslist = Arrays.asList(arr);   //长度不可变的list'
			LinkedList<String> linklist = new LinkedList<>(aslist);
			
			//是否包含id、
			if (linklist.contains(id)) {
				linklist.remove(id);
				linklist.addFirst(id);
				
			}else {
				//判断是否长度>2
				if (linklist.size()>2) {
					linklist.removeLast();
					linklist.addFirst(id);
				}else {
					linklist.addFirst(id);
				}
				
			}
			
			//将list转成字符串
			ids="";
			for(String s:linklist) {
				ids+=(s+"-");
			}
			
			//处理最后一个“-”
			ids=ids.substring(0, ids.length()-1);
			
		}
		System.out.println("ids:"+ids);
		
		//将ids写回去
		c = new Cookie("ids", ids);
		//设置访问路径
		c.setPath(request.getContextPath()+"/");
		//设置存活时间
		c.setMaxAge(3600);
		//写回浏览器
		response.addCookie(c);
		
		//跳转页面
		
		response.sendRedirect(request.getContextPath()+"/product_info"+id+".htm");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
