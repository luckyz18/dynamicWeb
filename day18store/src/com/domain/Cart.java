package com.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *	 购物车
 * @author 祝丽华
 *
 */
public class Cart {
	
	private Map<String, CartItem> map = new LinkedHashMap<>();

	private double total = 0.0;
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getTotal() {
		return total;
	}
	
	/**
	 * 获取所有购物车项
	 * @return 
	 */
	public Collection<CartItem> getItems() {
		return map.values();
	}
	
	/**
	 * 	添加到购物车
	 * @param item
	 */
	public void add2Cart(CartItem item) {
		//1. 有无该商品
		String pid = item.getProduct().getPid();
		if (map.containsKey(pid)) {
			//有 获取之前数量  + 现在数量
			CartItem oitem = map.get(pid);
			Integer oCount = oitem.getCount();
//			item.setCount(item.getCount() + oCount);  //有错 这样购物车数量并没有增加
			oitem.setCount(item.getCount() + oCount);
			
		}else {
			//无 直接加入购物车
			map.put(pid, item);
		}
		
		//2.修改金额
		total += item.getSubtotal();
	}
	
	/**
	 * 从购物车移除指定购物车项
	 * @param pid
	 */
	public void removeFromCart(String pid) {
		//第一种写法空指针的错误  移除了再找空指针。。。
//		map.remove(pid);
//		total -= map.get(pid).getSubtotal();
		
		CartItem item = map.remove(pid);
		total -= item.getSubtotal();
	}
	
	/**
	 * 清空购物车
	 */
	public void clearCart() {
		map.clear();
		total = 0.0;
	}

}
