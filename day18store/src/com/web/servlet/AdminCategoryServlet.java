package com.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.domain.Category;
import com.service.CategoryService;
import com.utils.BeanFactory;
import com.utils.UUIDUtils;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 后台分类管理
	 * @throws Exception 
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CategoryService cs = (CategoryService) BeanFactory.getbean("CategoryService");
		List<Category> list = cs.findAll();
		request.setAttribute("list", list);
		return "/admin/category/list.jsp";
		
	}
	
	/**
	 * 添加分类UI
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/admin/category/add.jsp";
	}
	
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取cname cid
		String cname = request.getParameter("cname");
		
		Category category = new Category();
		category.setCid( UUIDUtils.getId());
		category.setCname(cname);
		
		//service
		CategoryService cs = (CategoryService) BeanFactory.getbean("CategoryService");
		cs.add(category);
		
		//重定向
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		return null;
		
	}
	/**
	 * 通过id获取一个分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		CategoryService cs = (CategoryService) BeanFactory.getbean("CategoryService");
		Category category = cs.getById(cid);

		request.setAttribute("category",category);
		//请求转发
		return "/admin/category/edit.jsp";
	}
	
	/**
	 * 编辑分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		
		//service
		CategoryService cs = (CategoryService) BeanFactory.getbean("CategoryService");
		cs.update(category);
		
		//重定向,
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		return null;
	}
	
	/**
	 * 删除某个分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		
		CategoryService cs = (CategoryService) BeanFactory.getbean("CategoryService");
	
		
		//删除该分类
		cs.delete(cid);
		
		
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		
		return null;
		
	}
}
