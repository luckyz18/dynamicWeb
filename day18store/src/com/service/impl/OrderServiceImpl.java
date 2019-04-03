package com.service.impl;

import com.dao.OrderDao;
import com.domain.Order;
import com.domain.OrderItem;
import com.service.OrderService;
import com.utils.BeanFactory;
import com.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService{

	/**
	 * 	提交订单
	 * @throws Exception 
	 */
	@Override
	public void add(Order order) throws Exception{
		try {
			//1.开启事务
			DataSourceUtils.startTransaction();
		
			OrderDao dao = (OrderDao) BeanFactory.getbean("OrderDao");
			//2.向order表添加一个订单
			dao.add(order);
			
			//3.向orderItem表中添加  n 条数据
			for (OrderItem oitem: order.getItems()) {
				dao.addItem(oitem);
			}
			
			//4.提交事务
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
		
	}

}
