package com.jquery_Ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.SearchKwService;

import net.sf.json.JSONArray;

/**
 *     搜索关键字
 */
public class SearchKw4AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		//接收参数
		String kw = request.getParameter("kw");

		
		//调用service
		List<Object>  list = null;
		try {
		    list =new SearchKwService().findKw4Ajax(kw);
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//将数据[a,aa,aac] 去掉括号写回去 a,aa,aac
		/*if (list!= null && list.size()>0) {
			String s = list.toString();
			s = s.substring(1, s.length()-1);
			System.out.println(s);
			
			response.getWriter().println(s);
		}*/
		
		//用json改写上边
		if (list!= null && list.size()>0) {
			System.out.println("输出----"+JSONArray.fromObject(list));
			response.getWriter().println(JSONArray.fromObject(list));
			
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
