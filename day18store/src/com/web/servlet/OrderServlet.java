package com.web.servlet;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Cart;
import com.domain.CartItem;
import com.domain.Order;
import com.domain.OrderItem;
import com.domain.User;
import com.service.OrderService;
import com.utils.BeanFactory;
import com.utils.UUIDUtils;

/**
 * 	订单模块相关
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * 提交订单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 提交订单
		//用户是否登录
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			String msg = "请先登录~~~";
			request.setAttribute("msg", msg);
			return "/jsp/msg.jsp";
		}
		
		//封装数据
		/**
		 * private String oid;
			private Date ordertime;
			private Double total;
			private Integer state=0; //0：未支付   1：已支付	
			private String address;
			private String name;
			private String telephone;
			private User user;
			private List<OrderItem> items  = new LinkedList<>();
		 */
//		//注册转换器		
//		ConvertUtils.register(new myconvert(),Date.class);
		
		Order order = new Order();
		order.setOid(UUIDUtils.getId());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(sdf.format(new Date()));
		System.out.println("time"+time);
		
		order.setOrdertime(time);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		order.setTotal(cart.getTotal());
		
		order.setUser(user);
		/**
		 * 	封装订单项
		 */
		/**
		 * `itemid` varchar(32) NOT NULL,
		  `count` int(11) DEFAULT NULL,
		  `subtotal` double DEFAULT NULL,
		  `pid` varchar(32) DEFAULT NULL,
		  `oid` varchar(32) DEFAULT NULL,
		 */
		for (CartItem item: cart.getItems()) {
			
			OrderItem oItem = new OrderItem();
			
			oItem.setItemid(UUIDUtils.getId());
			oItem.setCount(item.getCount());
			oItem.setSubtotal(item.getSubtotal());
			oItem.setOrder(order);
			oItem.setProduct(item.getProduct());
			
			//添加到list当中
			order.getItems().add(oItem);
		}
		

		//service
		OrderService oService = (OrderService) BeanFactory.getbean("OrderService");
		oService.add(order);
		
		//添加到域  跳转
		request.setAttribute("order", order);
		
		//订单提交之后 需要清空购物车

//		request.getSession().removeAttribute("cart");  //这样也可以
		cart = (Cart) request.getSession().getAttribute("cart");
		cart.clearCart();
		
		return "/jsp/order_info.jsp";
	}


	private Cart getCart(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
