package com.web.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Cart;
import com.domain.CartItem;
import com.domain.Order;
import com.domain.OrderItem;
import com.domain.Pagebean;
import com.domain.User;
import com.service.OrderService;
import com.utils.BeanFactory;
import com.utils.PaymentUtil;
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

	/**
	 * 分页 查询我的订单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findAllByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 分页 查询我的订单
		//获取用户
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "请先登录");
			return "/jsp/msg.jsp";
		}
		
		//获取currPage
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		int pageSize=3;
		
		//service  封装成 pagebean
		OrderService os = (OrderService) BeanFactory.getbean("OrderService");
		Pagebean<Order> bean = os.findAllByPage(currPage,pageSize,user);
		
		//域
		request.setAttribute("pb", bean);
		
		return "/jsp/order_list.jsp";
		
	}
	
	/**
	 * 查询订单详情
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 查询订单详情
		//获取oid
		String oid = request.getParameter("oid");
		
		//service
		OrderService os = (OrderService) BeanFactory.getbean("OrderService");
		Order order = os.getById(oid);
		
		//域
		request.setAttribute("order", order);
		
		return "/jsp/order_info.jsp";
		
	}
	
	/**
	 * 订单支付 提交给第三方 
	 * @param request
	 * @param respone
	 * @return
	 * @throws Exception
	 */
	public String pay(HttpServletRequest request,HttpServletResponse respone) throws Exception{
		// TODO 订单支付 提交给第三方 
		//接受参数
		String address=request.getParameter("address");
		String name=request.getParameter("name");
		String telephone=request.getParameter("telephone");
		String oid=request.getParameter("oid");
		
		
		//通过id获取order
		OrderService s=(OrderService) BeanFactory.getbean("OrderService");
		Order order = s.getById(oid);
		
		order.setAddress(address);
		order.setName(name);
		order.setTelephone(telephone);
		
		//更新order
		s.update(order);
		

		// 组织发送第三方支付公司需要哪些数据
		String pd_FrpId = request.getParameter("pd_FrpId");
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");   //工具类用来读取配置信息
		String p2_Order = oid;
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
		// 第三方支付可以访问网址
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		// 加密hmac 需要密钥
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
	
		
		//发送给第三方
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		respone.sendRedirect(sb.toString());
		
		return null;
	}
	
	/**
	 * 支付结果返回的 的回调函数
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String callback(HttpServletRequest request,HttpServletResponse response) throws Exception{
		// TODO 支付结果返回的 的回调函数
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		
		// 身份校验 --- 判断是不是支付公司通知你
		String hmac = request.getParameter("hmac");
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");

		// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 响应数据有效
			if (r9_BType.equals("1")) {
				// 浏览器重定向
				System.out.println("111");
				request.setAttribute("msg", "您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");
				
			} else if (r9_BType.equals("2")) {
				// 服务器点对点 --- 支付公司通知你
				System.out.println("付款成功！222");
				// 修改订单状态 为已付款
				// 回复支付公司
				response.getWriter().print("success");
			}
			
			//修改订单状态
			OrderService s=(OrderService) BeanFactory.getbean("OrderService");
			Order order = s.getById(r6_Order);
			order.setState(1);
			
			s.update(order);
			
		} else {
			// 数据无效
			System.out.println("数据被篡改！");
		}
		
		
		return "/jsp/msg.jsp";
		
	}
	
	/**
	 * 修改订单状态
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String updateState(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String oid = request.getParameter("oid");
		String state = request.getParameter("state");
		
		//service
		OrderService os = (OrderService) BeanFactory.getbean("OrderService");
		Order order = os.getById(oid);
		order.setState(Integer.parseInt(state));
		
		os.update(order);
		
		response.sendRedirect(request.getContextPath()+"/order?method=findAllByPage&currPage=1");
		
		return null;
		
	}
	
	
	
	
	
	
}