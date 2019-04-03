package com.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Cart;
import com.domain.CartItem;
import com.domain.Product;
import com.service.ProductService;
import com.utils.BeanFactory;

/**
 * 	购物车
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	获取购物车
	 * @param request
	 * @return
	 */
	public Cart getCart(HttpServletRequest request) {
		//不每次都从new 获取  session
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	

    /**
     * 	添加到购物车
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public String add(HttpServletRequest request, HttpServletResponse response)throws Exception {
		//1.获取参数
		String pid = request.getParameter("pid");
		Integer count= Integer.parseInt(request.getParameter("count"));
		
		//2.获得该商品
		ProductService ps = (ProductService) BeanFactory.getbean("ProductService");
		Product product = ps.getByPid(pid);
		
		//3.item
		CartItem item = new CartItem(product, count);
		
		
		//4.添加到购物车    remove clear等方法都需要获取购物车  故而写成一个方法
		Cart cart = this.getCart(request);
		cart.add2Cart(item);
		
		//5.重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
		
	}
	/**
	 * 	删除某商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public String remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		System.out.println("pid:"+pid);
		//获取购物车
		getCart(request).removeFromCart(pid);
		
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
	
	/**
	 * 清空购物车
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取购物车  调用clearCart
		this.getCart(request).clearCart();
		
//		
//		request.getSession().invalidate();
		
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
		
	}
}
