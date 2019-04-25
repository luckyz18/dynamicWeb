package com.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Order;
import com.domain.OrderItem;
import com.service.OrderService;
import com.utils.BeanFactory;
import com.utils.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

/**
 * 	订单模块相关
 */
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

   /**
        *   根据订单状态   查询订单
    * @param request
    * @param response
    * @return
    * @throws Exception 
    */
	public String findAllByState(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String state = request.getParameter("state");
		String state=request.getParameter("state");
		//service
		OrderService os = (OrderService) BeanFactory.getbean("OrderService");
		List<Order> list = os.findAllByState(state);
		
		request.setAttribute("list", list);
		return "/admin/order/list.jsp";
	}
	
	/**
	 *   根据订单id 获取订单详情
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getDetailByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		
		String oid = request.getParameter("oid");
		
		OrderService os = (OrderService) BeanFactory.getbean("OrderService");
		List<OrderItem> items = os.getById(oid).getItems();
		
		//将list转成json返回
		//排除不用写回的数据
		JsonConfig config = JsonUtil.configJson(new String[] {"itemid","class","order"});
		JSONArray json = JSONArray.fromObject(items, config);
		
//		System.out.println(json);
		response.getWriter().print(json);
		return null;
			
	}
	/**
	 * 更新订单状态
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String updateState(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid = request.getParameter("oid");
		String state = request.getParameter("state");
		
		//service
		OrderService os = (OrderService) BeanFactory.getbean("OrderService");
		Order order = os.getById(oid);
		order.setState(2);
		
		os.update(order);
		
		response.sendRedirect(request.getContextPath()+"/adminOrder?method=findAllByState&state=1");
		
		return null;
		
	}

}
