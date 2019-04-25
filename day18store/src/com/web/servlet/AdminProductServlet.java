package com.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Category;
import com.domain.Pagebean;
import com.domain.Product;
import com.service.CategoryService;
import com.service.ProductService;
import com.utils.BeanFactory;

/**
 * 后台商品模块相关
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 展示所有商品 不分页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO 展示所有商品
		
		ProductService ps=(ProductService) BeanFactory.getbean("ProductService");
		List<Product> list = ps.findAll();
		
		request.setAttribute("list", list);
		
	
		return "/admin/product/list.jsp";
		
	}
	
	/**
	 * 查询已下架商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findAllByStopSell(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductService ps=(ProductService) BeanFactory.getbean("ProductService");
		List<Product> list = ps.findAllByStopSell();
		
		request.setAttribute("list", list);
		return "/admin/product/list.jsp";
		
	}
	
	/**
	 *   展示所有商品 test 分页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findAllByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		int pageSize = 10;
		
		ProductService ps = (ProductService) BeanFactory.getbean("ProductService");
		Pagebean<Product> bean= ps.findAllByPage(currPage,pageSize);
		
		request.setAttribute("pb",bean );
		
		return "/admin/product/list.jsp";
		
	}
	/**
	 * 	添加商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//查询出所有分类
		CategoryService cs= (CategoryService) BeanFactory.getbean("CategoryService");
		List<Category> list = cs.findAll();
		
		request.setAttribute("clist", list);
		return "/admin/product/add.jsp";
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String stopSellByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		
		//service
		ProductService ps = (ProductService) BeanFactory.getbean("ProductService");
//		Product p = ps.getByPid(pid);
		
//		p.setPflag(1);  //设置成已下架  1代表已下架
		ps.stopSellByPid(pid);
		
		response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll");
		return null;
		
	}
}
