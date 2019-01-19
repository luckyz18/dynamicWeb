package com.request.param;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求参数
 */
public class ParamServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println("username:"+username);
		String[] hobby = request.getParameterValues("hobby");
		System.out.println("hobby:"+Arrays.toString(hobby));
		
		System.out.println("-----------------------");
		Map<String, String[]> map = request.getParameterMap();
		for  (String key : map.keySet()) {
			System.out.println(key+"  "+Arrays.toString(map.get(key)));
		}
		
		System.out.println("======================");
		Map<String, String[]> map_ = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, String[]> entry = it.next();
			System.out.println(entry.getKey()+"  "+Arrays.toString(entry.getValue()));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
