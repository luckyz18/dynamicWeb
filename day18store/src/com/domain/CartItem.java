package com.domain;

import java.io.Serializable;

/**
 * 	购物车项
 * @author 祝丽华
 *
 */
public class CartItem implements Serializable{
	private Product product;
	private Integer count;
	private double subtotal= 0.0;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public double getSubtotal() {
		return product.getShop_price() * count;
	}
	
	public CartItem(Product product, Integer count) {
		super();
		this.product = product;
		this.count = count;
	}
	public CartItem() {
		super();
	}
	
	
	
	
	
	
	
	
	
}
