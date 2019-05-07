package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.domain.Customer;
import com.domain.Linkman;
import com.service.CustomerService;
import com.service.LinkmanService;
import com.utils.HibernateUtils;

public class LinkmanServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * 	初始化添加联系人  先把客户查到  
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String initAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerService cs = new CustomerService();
		List<Customer> list = cs.getAll(null);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/linkman/add.jsp").forward(request, response);
		return null;
	}
	/**
	 * 添加联系人 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addsubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		String cust_id = map.get("cust_id")[0];
	    Long id = Long.parseLong(cust_id);
		Linkman linkman = new Linkman();
		try {
			BeanUtils.populate(linkman, map);
			LinkmanService lkms = new LinkmanService();
			lkms.add(linkman,id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 联系人列表
	 * 离线查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lkmName = request.getParameter("lkmName");
		/*离线对象*/
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		if (lkmName != null && !lkmName.trim().isEmpty()) {
			criteria.add(Restrictions.like("lkm_name", "%"+lkmName+"%"));
		}
		LinkmanService ls = new LinkmanService();
		List<Linkman> list = ls.list(criteria);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/linkman/list.jsp").forward(request, response);
		return null;
		
	}
	
	

}
