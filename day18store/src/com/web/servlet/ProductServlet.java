package com.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Pagebean;
import com.domain.Product;
import com.service.ProductService;
import com.service.impl.ProductServiceImpl;
import com.utils.CookieUtils;

/**
 * 关于商品的servlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * 根据pid 找到一个商品
	 */
	public String getByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 根据pid 找到一个商品
		String pid = request.getParameter("pid");
		ProductService s = new ProductServiceImpl();
		Product p = null;
		try {
			p = s.getByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("bean", p);
		
		//商品浏览记录 cookie 部分
		
		//判断cookie是否为空
//		String id = request.getParameter("pid");
		System.out.println("request.getCookies():"+request.getCookies());
		Cookie c = CookieUtils.getCookieByName("ids",request.getCookies());
		System.out.println("c:"+c);
		
		
		//若cookie为空
		String ids = "";
		if (c == null) {
			ids=pid;
		}else {
			//若cookie不空  继续判断ids是否包含id  ids=1-2-3
			//获取值
			ids=c.getValue();
			System.out.println(ids);
			//分割ids
			String[] arr = ids.split("-");   //分割成数组存储
			List<String> aslist = Arrays.asList(arr);   //长度不可变的list' 集合
			LinkedList<String> linklist = new LinkedList<>(aslist);
			
			//是否包含id、
			if (linklist.contains(pid)) {
				linklist.remove(pid);
				linklist.addFirst(pid);
				
			}else {
				//判断是否长度>2
				if (linklist.size()>2) {
					linklist.removeLast();
					linklist.addFirst(pid);
				}else {
					linklist.addFirst(pid);
				}
				
			}
			
			//将list转成字符串
			ids="";
			for(String s1:linklist) {
				ids+=(s1+"-");
			}
			
			//处理最后一个“-”
			ids=ids.substring(0, ids.length()-1);	//截   ids=3-2-1 
			
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
		
		return "/jsp/product_info.jsp";
	}
	
	/**
	 * 根据分类，分页展示商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取类别  当前页 设置pageSize
		String cid = request.getParameter("cid");
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		int pageSize=12;
		
		//service
		ProductService s = new ProductServiceImpl();
		Pagebean<Product> bean = s.findByPage(cid,currPage,pageSize);
		
		request.setAttribute("pbean", bean);
		
		return "/jsp/product_list.jsp";
		
	}

}
