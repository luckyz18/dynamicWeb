package com.jquery_Ajax;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.City;
import com.service.CityService;

import net.sf.json.JSONArray;

/**
 * 	根据省 id 查找相应的市
 */
public class SearchCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		//省 id
		String pid = request.getParameter("pid");
		
		//service
		List<City> list = null;
		try {
			list = new CityService().findCityByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//返回json
		if(list!= null && list.size()>0) {
			//System.out.println(JSONArray.fromObject(list));
			response.getWriter().println(JSONArray.fromObject(list));
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
