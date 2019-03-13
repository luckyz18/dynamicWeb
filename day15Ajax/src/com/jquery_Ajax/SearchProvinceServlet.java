package com.jquery_Ajax;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Province;
import com.service.ProvinceService;

import net.sf.json.JSONArray;

/**
 * 	 省市联动
 */
public class SearchProvinceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		//service
		List<Province> list =null;
		try {
			list = new ProvinceService().findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//json写回
		if (list!= null && list.size()>0) {
			//System.out.println(JSONArray.fromObject(list));
			response.getWriter().println(JSONArray.fromObject(list));
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
